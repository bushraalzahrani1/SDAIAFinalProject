package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;


import jakarta.ws.rs.core.*;
import org.example.DAO.ScheduleDAO;
import org.example.DTO.ScheduleDTO;
import org.example.Mapper.ScheduleMapper;
import org.example.Model.Schedule;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.Exception.DataNotFoundException;

@Path("/SCHEDULES")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
public class ScheduleController {

    @Inject
    ScheduleDAO dao = new ScheduleDAO();

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;

    @GET
    public Response SELECT_ALL_SCHEDULE(

    ) {

        try {
            GenericEntity<ArrayList<Schedule>> schedule = new GenericEntity<ArrayList<Schedule>>(dao.SELECT_ALL_SCHEDULE()) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(schedule)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(schedule)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(schedule, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @GET
    @Path("{schedule_id}")
    public Response SELECT_ONE_SCHEDULE(@PathParam("schedule_id") int schedule_id)throws SQLException {

        try {
            Schedule schedule = dao.SELECT_ONE_SCHEDULE(schedule_id);
            if(schedule == null ){

                throw new DataNotFoundException("consultation " + schedule_id + "Not found");
            }

            ScheduleDTO dto = ScheduleMapper.INSTANCE.toSecDto(schedule);
            addLinks(dto);

            return Response.ok(dto, MediaType.APPLICATION_JSON).build();


        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }
    private void addLinks(ScheduleDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docUri = uriInfo.getAbsolutePathBuilder()
                .path(ScheduleController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docUri.toString(), "schedule");

    }

    @POST
    public Response INSERT_SCHEDULE(Schedule schedule) {

        try {
            dao.INSERT_SCHEDULE(schedule);
            return Response
                    .ok(schedule)
                    .type(MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{schedule_id}")
    public Response UPDATE_SCHEDULE(@PathParam("schedule_id") int schedule_id, Schedule schedule) {

        try {
            schedule.setSchedule_id(schedule_id);
            dao.UPDATE_SCHEDULE(schedule);

            URI uri = uriInfo.getAbsolutePathBuilder().path(schedule.getSchedule_id() + "").build();

            return Response
                    .created(uri)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
