package org.vnguyen.joreman;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	
	protected ScheduledThreadPoolExecutor executor;
	
	public ForemanClientFactory() {
		executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
	
		registerShutdownHooks();
	}
	
	
	public static ForemanAPI createAPI(Config config) {
		return createAPI(config.foreman_url, config.foreman_user, config.foreman_password);
	}
	
	public static ForemanAPI createAPI(String url, String username, String password) {
		return ProxyFactory.create(	ForemanAPI.class, 
									url, 
									HTTPHelper.basicHttpAuthExecutor(username, password) 
								);	
	}
	

	
	public ForemanClient createClient()  {
		Config config = Config.load();
		return createClient(config);
	}
	
	public ForemanClient createClient(Config config) {
		ForemanClient client = new ForemanClient(createAPI(config));
		client.setExecutor(executor);
		return client;
	}
	
	private void registerShutdownHooks() {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {

			public void run() {
				executor.shutdown();
				try {
					executor.awaitTermination(2, TimeUnit.MINUTES);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
	}
}
