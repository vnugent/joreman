package org.vnguyen.joreman;

import java.io.InputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HostFormBuilder extends Host {
	public static final String DEFAULT_SIMPLE_HOST_JSON = "/templates/simple.host.json";

	/**
	 * Prepare a host form using data from a default json file
	 * @param hostName
	 * @return
	 * @throws Exception
	 */
	public static Host newTemplate(String hostName) throws Exception {
		return newTemplate(hostName, DEFAULT_SIMPLE_HOST_JSON);
	}
	
	public static Host newTemplate(String hostName, String jsonFile) throws Exception {
		Host host = newTemplate(HostFormBuilder.class.getClass().getResourceAsStream(jsonFile));
		host.name = hostName;
		return host;
	}
	
	public static Host newTemplate(InputStream jsonIS) throws Exception {
		ContextResolver<ObjectMapper> ctx = ResteasyProviderFactory.getInstance().getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		Host host = ctx.getContext(null).readValue(jsonIS, Host.class);
		return host;
	}
	
	public static String toJson(Host host) throws Exception {
		ContextResolver<ObjectMapper> ctx = ResteasyProviderFactory.getInstance().getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		return ctx.getContext(null).writerWithDefaultPrettyPrinter().writeValueAsString(host);
	}
}
