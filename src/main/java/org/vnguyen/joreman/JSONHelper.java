package org.vnguyen.joreman;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHelper {
	
	public static <T> T load(Class<T> clz, String jsonFile) {
		ContextResolver<ObjectMapper> ctx = ResteasyProviderFactory.getInstance().getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		InputStream is = JSONHelper.class.getResourceAsStream("/templates/"+jsonFile);

		try {
			if (is != null) {
				return ctx.getContext(null).readValue(is, clz);
			} else {
				File f = new File(jsonFile);
				return ctx.getContext(null).readValue(f , clz);}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String toJson(Host host) throws Exception {
		ContextResolver<ObjectMapper> ctx = ResteasyProviderFactory.getInstance().getContextResolver(ObjectMapper.class, MediaType.APPLICATION_JSON_TYPE);
		return ctx.getContext(null).writerWithDefaultPrettyPrinter().writeValueAsString(host);
	}

}
