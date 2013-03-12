package org.vnguyen.joreman;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface ForemanAPI {
	@GET
	@Path("/api/hosts/{id}")
	@Produces("text/plain")	
	Host getHost(@PathParam("id") String id);
	

	@GET
	@Path("/api/compute_resources/{id}/{vms}")
	@Produces("text/plain")	
	String getComputes(@PathParam("id") String id, @PathParam("vms") String vms);
	
	
	@POST
	@Path("/api/hosts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Host newHost(Host newHost);	


	@PUT
	@Path("/api/hosts/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String powerOn(@PathParam("name") String name, HostPower2 power);	
	
	
	@GET
	@Path("/api/hostgroups/{id}")
	@Produces("text/plain")	
	String getHostGroups(@PathParam("id") String id);	
	
	
	@GET
	@Path("/api/status")
	@Produces(MediaType.APPLICATION_JSON)
	String status();	
	
}
