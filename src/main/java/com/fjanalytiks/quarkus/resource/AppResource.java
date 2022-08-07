package com.fjanalytiks.quarkus.resource;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
@RequestScoped
public class AppResource {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreeting() {
        return Response.ok("{\"message\":\"Welcome to FJAnalytiks Sentimental Analysis API\"}").build();
    }
}
