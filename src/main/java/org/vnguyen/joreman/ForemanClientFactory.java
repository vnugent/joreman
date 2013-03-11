package org.vnguyen.joreman;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class ForemanClientFactory {

	static {
		ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
		factory.registerProvider(JacksonJaxbJsonProvider.class);
		factory.registerProvider(JacksonContextResolver.class);
		RegisterBuiltin.register(factory);		
	}
	
	public static ForemanAPI create(String url, String username, String password) {
		return ProxyFactory.create(	ForemanAPI.class, 
									url, 
									HTTPHelper.basicHttpAuthExecutor(username, password) 
								);	
	}
}
