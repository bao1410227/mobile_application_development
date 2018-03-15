package dev.phanng.wstest.helloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import java.lang.Math;

@Path("")
public class HelloWorld {
	@GET
	@Path("/")
	public Response getHelloWord(
			@QueryParam("lat1") double lat1,
			@QueryParam("lon1") double lon1,
			@QueryParam("lat2") double lat2,
			@QueryParam("lon2") double lon2) { 
		return Response.status(200)
				.entity("Distance : " + getDistance(lat1,lon1,lat2,lon2) + " m")
				.build();
	}
	
	// Calculate Earth distance between 2 points using Haversine formula
	double getDistance(double lat1, double lon1, double lat2, double lon2) {
		// Earth's radius
		double R = 6371e3;
		double phi1 = Math.toRadians(lat1);
		double phi2 = Math.toRadians(lat2);
		double delta_phi = Math.toRadians(lat2-lat1);
		double delta_lambda = Math.toRadians(lon2-lon1);
		double a = Math.sin(delta_phi/2) * Math.sin(delta_phi/2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(delta_lambda/2) * Math.sin(delta_lambda/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		// The distance
		double d = R * c;
				
		return d;

	}
}
