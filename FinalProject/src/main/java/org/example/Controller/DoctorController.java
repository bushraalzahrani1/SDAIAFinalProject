package org.example.Controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.core.*;
import org.example.DTO.DoctorFilterDTO;
import org.example.DAO.DoctorDAO;
import jakarta.ws.rs.*;

import org.example.DTO.DoctorDTO;
import org.example.Mapper.DoctorMapper;
import org.example.Model.Doctor;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.Exception.DataNotFoundException;

@Path("/DOCTORS")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
public class DoctorController {

    @Inject
    DoctorDAO dao;

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    public Response SELECT_ALL_DOCTORS(
            @BeanParam DoctorFilterDTO filter
    ) {

        try {
            GenericEntity<ArrayList<Doctor>> doctor = new GenericEntity<ArrayList<Doctor>>(dao.SELECT_ALL_DOCTORS(filter)) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(doctor)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(doctor)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(doctor, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{doctor_id}")
    public Response SELECT_ONE_DOCTOR(@PathParam("doctor_id") int doctor_id)throws SQLException {

        try {
            Doctor doctor = dao.SELECT_ONE_DOCTOR(doctor_id);
            if(doctor == null ){

                throw new DataNotFoundException("doctor " + doctor_id + "Not found");
            }
            //headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML) {

            DoctorDTO dto = DoctorMapper.INSTANCE.toDocDto(doctor);
            addLinks(dto);

            return Response.ok(dto)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
            /* return Response
                    .ok(dto)
                    .type(MediaType.APPLICATION_JSON)
                    .build(); */

        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(DoctorDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docUri = uriInfo.getAbsolutePathBuilder()
                .path(DoctorController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docUri.toString(), "doctors");

    }

    @POST
    public Response INSERT_DOCTOR(Doctor doctor) {

        try {
            dao.INSERT_DOCTOR(doctor);
            return Response
                    .ok(doctor)
                    .type(MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{doctor_id}")
    public Response UPDATE_DOCTOR(@PathParam("doctor_id") int doctor_id, Doctor doctor) {

        try {
            doctor.setDoctor_id(doctor_id);
            dao.UPDATE_DOCTOR(doctor);

            URI uri = uriInfo.getAbsolutePathBuilder().path(doctor.getDoctor_id() + "").build();

            return Response
                    .created(uri)
                    .type(MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
