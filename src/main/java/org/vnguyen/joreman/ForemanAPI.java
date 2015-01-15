package org.vnguyen.joreman;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.vnguyen.joreman.model.HG;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.model.HostPowerController;
import org.vnguyen.joreman.model.HostWrapper;
import org.vnguyen.joreman.model.Image;


public interface ForemanAPI {
	@GET
	@Path("/api/compute_resources/{compute_res_id}/images/{image_id}")
	@Produces(MediaType.APPLICATION_JSON)	
	Image getImage(@PathParam("compute_res_id") String computeResourceId, @PathParam("image_id") String imageId);
	
	@GET
    @Path("/api/hosts/{id}")
    @Produces(MediaType.APPLICATION_JSON)   
    Host getHost(@PathParam("id") String id);
	

	@GET
	@Path("/api/compute_resources/{id}/{vms}")
	@Produces("text/plain")	
	String getComputes(@PathParam("id") String id, @PathParam("vms") String vms);
	
	
	@POST
	@Path("/api/hosts")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Host newHost(HostWrapper newHost);	
	
	@PUT
	@Path("/api/hosts/{id}/power")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	HostPowerController.PowerStatus hostPower(@PathParam("id") String id, HostPowerController.PowerAction actionCl);	
	
	@GET
	@Path("/api/hosts/{id}/status")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String getHostStatus(@PathParam("id") String id);		
	
	@GET
	@Path("/api/hostgroups/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	HG getHostGroup(@PathParam("id") String id);	
	
	@GET
	@Path("/api/hostgroups")
	@Produces(MediaType.APPLICATION_JSON)
	String getHostGroups(@QueryParam("search") String name, @QueryParam("order") String order, @QueryParam("page") String page, @QueryParam("per_page") String perPage);		
	
	@GET
	@Path("/api/status")
	@Produces(MediaType.APPLICATION_JSON)
	String status();

/*	@DELETE
	@Path("/api/hosts/{hostName}")
	void deleteHost(@PathParam("hostName") String hostName);*/
	
	@DELETE
	@Path("/api/hosts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Host deleteHost(@PathParam("id") String hostId);
	
	@GET
	@Path("/api/hosts")
	@Produces(MediaType.APPLICATION_JSON)
	String getHosts(@QueryParam("search") String name, @QueryParam("order") String order, @QueryParam("page") String page, @QueryParam("per_page") String perPage);	
	
}
