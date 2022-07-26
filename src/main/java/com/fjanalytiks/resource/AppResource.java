package com.fjanalytiks.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class AppResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreeting() {
        return Response.ok("{\"message\":\"Welcome to FJAnalytiks Sentimental Analysis API\"}").build();
    }
}
