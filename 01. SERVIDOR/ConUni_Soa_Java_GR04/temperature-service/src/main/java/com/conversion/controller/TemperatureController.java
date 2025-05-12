package com.conversion.controller;


import com.conversion.service.TemperatureService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/temperature")
public class TemperatureController {
    TemperatureService service = new TemperatureService();

    @GET
    @Path("/toCelsius")
    @Produces(MediaType.APPLICATION_JSON)

    public Response convertToCelsius(@QueryParam("fahrenheit") double f) {
        double celsius = service.toCelsius(f);
        return Response.ok("{\"celsius\":"+celsius + "}").build();
    }

    @GET
    @Path("/toFahrenheit")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertToFahrenheit(@QueryParam("celsius") double c) {
        double fahrenheit = service.toFahrenheit(c);
        return Response.ok("{\"fahrenheit\": " + fahrenheit + "}").build();
    }
}
