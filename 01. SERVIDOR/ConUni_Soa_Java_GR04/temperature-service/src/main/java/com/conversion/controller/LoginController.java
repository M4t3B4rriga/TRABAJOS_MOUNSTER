package com.conversion.controller;


import com.conversion.model.User;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")

public class LoginController {
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        if ("mounster".equals(user.getUsername()) && "mounster9".equals(user.getPassword())) {
            return Response.ok("{\"token\": \"login_ok\"}").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
