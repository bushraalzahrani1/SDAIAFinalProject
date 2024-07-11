package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.*;
import org.example.DTO.DoctorFilterDTO;
import org.example.DAO.ConsultationDAO;
import jakarta.ws.rs.*;

import org.example.DTO.ConsultationDTO;
import org.example.Mapper.ConsultationMapper;
import org.example.Model.Consultation;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.Exception.DataNotFoundException;


@Path("/CONSULTATIONS")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
public class ConsultationController {

    @Inject
    ConsultationDAO dao;

    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    public Response SELECT_ALL_CONSULTATIONS(
            @BeanParam DoctorFilterDTO filter
    ) {

        try {
            GenericEntity<ArrayList<Consultation>> consultation = new GenericEntity<ArrayList<Consultation>>(dao.SELECT_ALL_CONSULTATIONS()) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(consultation)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(consultation)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(consultation, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{consultation_id}")
    public Response SELECT_ONE_CONSULTATIONS(@PathParam("consultation_id") int consultation_id)throws SQLException {

        try {
            Consultation consultation = dao.SELECT_ONE_CONSULTATIONS(consultation_id);
            if(consultation == null ){

                throw new DataNotFoundException("consultation " + consultation_id + "Not found");
            }

            ConsultationDTO dto = ConsultationMapper.INSTANCE.toConDto(consultation);
            addLinks(dto);

            return Response.ok(dto, MediaType.APPLICATION_JSON).build();


        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    private void addLinks(ConsultationDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docUri = uriInfo.getAbsolutePathBuilder()
                .path(ConsultationController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docUri.toString(), "consultation");

    }

    @POST
    public Response INSERT_CONSULTATIONS(Consultation consultation) {

        try {
            dao.INSERT_CONSULTATIONS(consultation);
            return Response
                    .ok(consultation , MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{consultation_id}")
    public Response UPDATE_CONSULTATIONS(@PathParam("consultation_id") int consultation_id, Consultation consultation) {

        try {
            consultation.setConsultation_id(consultation_id);
            dao.UPDATE_CONSULTATIONS(consultation);

            URI uri = uriInfo.getAbsolutePathBuilder().path(consultation.getConsultation_id() + "").build();

            return Response
                    .created(uri)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
