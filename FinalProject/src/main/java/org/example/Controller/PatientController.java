package org.example.Controller;



import jakarta.inject.Inject;
import jakarta.ws.rs.core.*;
import org.example.DTO.DoctorFilterDTO;
import org.example.DAO.PatientDAO;
import jakarta.ws.rs.*;

import org.example.DTO.PatientDTO;
import org.example.Mapper.PatientMapper;
import org.example.Model.Patient;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import org.example.Exception.DataNotFoundException;


@Path("/PATIENTS")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
public class PatientController {

    @Inject
    PatientDAO dao ;

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;



    @GET
    public Response SELECT_ALL_PATIENT(

    ) {

        try {
            GenericEntity<ArrayList<Patient>> patient = new GenericEntity<ArrayList<Patient>>(dao.SELECT_ALL_PATIENT()) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(patient)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(patient)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(patient, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GET
    @Path("{patient_id}")
    public Response SELECT_ONE_PATIENT(@PathParam("patient_id") int patient_id)throws SQLException {

        try {
            Patient patient = dao.SELECT_ONE_PATIENT(patient_id);
            if(patient == null ){

                throw new DataNotFoundException("consultation " + patient_id + "Not found");
            }

            PatientDTO dto = PatientMapper.INSTANCE.toPatDto(patient);
            addLinks(dto);

            return Response.ok(dto, MediaType.APPLICATION_JSON).build();


        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }
    private void addLinks(PatientDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docUri = uriInfo.getAbsolutePathBuilder()
                .path(PatientController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docUri.toString(), "Patient");

    }

    @POST
    public Response INSERT_PATIENT(Patient patient) {

        try {
            dao.INSERT_PATIENT(patient);
            return Response
                    .ok(patient)
                    .type(MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Path("{patient_id}")
    public Response UPDATE_PATIENT(@PathParam("patient_id") int patient_id, Patient patient) {

        try {
            patient.setPatient_id(patient_id);
            dao.UPDATE_PATIENT(patient);

            URI uri = uriInfo.getAbsolutePathBuilder().path(patient.getPatient_id() + "").build();

            return Response
                    .created(uri)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
