package org.vnguyen.joreman;

import java.io.IOException;
import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.vnguyen.joreman.Compute_Attributes.Interfaces_Attributes;
import org.vnguyen.joreman.Compute_Attributes.Volumes_Attributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Builders {
	public static final String DEFAULT_SIMPLE_HOST_JSON = "/templates/simple.host.json";
	
	public static Compute_Attributes newComputeAttribute() {
		Compute_Attributes result = new Compute_Attributes();
		result.memory = "1073741824";
		result.cores = "2";
		result.start = "0";
		result.template = "00000000-0000-0000-0000-000000000000";
		result.cluster="fcb97476-3365-11e2-94d7-5254009cc188";
		
		result.interfaces_attributes = new HashMap<String, Interfaces_Attributes>(2);
		result.interfaces_attributes.put("new_interfaces", 
				new Interfaces_Attributes("00000000-0000-0000-0000-000000000009","", ""));
		result.interfaces_attributes.put("new_interfaces11", 
				new Interfaces_Attributes("00000000-0000-0000-0000-000000000009","eth0", ""));
		
		
		
		result.volumes_attributes = new HashMap<String, Volumes_Attributes>(2);
		result.volumes_attributes.put("new_volumes", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "", "", "")
				);
		result.volumes_attributes.put("new_volumes", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "", "", "")
				);
		result.volumes_attributes.put("new_volumes12", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "8", "", "")
				);
				
		return result;
	}
	
	public static Host newHost(String name) {
		
		Host newHost = new Host();
		newHost.name = name;
		newHost.computeAttrs = newComputeAttribute();


		return newHost;
	}
		
	
	public static Host newTemplate(String jsonFile) throws Exception {
		ContextResolver<ObjectMapper> ctx = ResteasyProviderFactory.getInstance().getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		Host host = ctx.getContext(null).readValue(Builders.class.getClass().getResourceAsStream(jsonFile), Host.class);
		return host;
		
	}
	
}
