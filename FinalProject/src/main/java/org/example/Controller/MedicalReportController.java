package org.example.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;


import jakarta.ws.rs.core.*;
import org.example.DAO.MedicalReportDAO;
import org.example.DTO.MedicalReportDTO;
import org.example.Mapper.MedicalReportMapper;
import org.example.Model.MedicalReport;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.Exception.DataNotFoundException;


@Path("/MEDICAL_REPORTS")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})

public class MedicalReportController {

    @Inject
    MedicalReportDAO dao;

    @Context
    UriInfo uriInfo;
    @Context
    HttpHeaders headers;


    @GET
    public Response SELECT_ALL_MEDICAL_REPORTS(

    ) {

        try {
            GenericEntity<ArrayList<MedicalReport>> medicalReport = new GenericEntity<ArrayList<MedicalReport>>(dao.SELECT_ALL_MEDICAL_REPORTS()) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(medicalReport)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(medicalReport)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(medicalReport, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @GET
    @Path("{medical_report_id}")
    public Response SELECT_ONE_MEDICAL_REPORT(@PathParam("medical_report_id") int medical_report_id)throws SQLException {

        try {
            MedicalReport medicalReport = dao.SELECT_ONE_MEDICAL_REPORT(medical_report_id);
            if(medicalReport == null ){

                throw new DataNotFoundException("consultation " + medical_report_id + "Not found");
            }

            MedicalReportDTO dto = MedicalReportMapper.INSTANCE.toMedDto(medicalReport);
            addLinks(dto);

            return Response.ok(dto, MediaType.APPLICATION_JSON).build();


        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }
    private void addLinks(MedicalReportDTO dto) {
        URI selfUri = uriInfo.getAbsolutePath();
        URI docUri = uriInfo.getAbsolutePathBuilder()
                .path(MedicalReportController.class).build();

        dto.addLink(selfUri.toString(), "self");
        dto.addLink(docUri.toString(), "medicalReport");

    }


    @POST
    public Response INSERT_MEDICAL_REPORTS(MedicalReport medicalReport) {

        try {
            dao.INSERT_MEDICAL_REPORTS(medicalReport);
            return Response
                    .ok(medicalReport)
                    .type(MediaType.APPLICATION_JSON)
                    .status(Response.Status.CREATED)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{medical_report_id}")
    public Response UPDATE_MEDICAL_REPORTS(@PathParam("medical_report_id") int medical_report_id, MedicalReport medicalReport) {

        try {
            medicalReport.setMedical_report_id(medical_report_id);
            dao.UPDATE_MEDICAL_REPORTS(medicalReport);

            URI uri = uriInfo.getAbsolutePathBuilder().path(medicalReport.getMedical_report_id() + "").build();

            return Response
                    .created(uri)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
