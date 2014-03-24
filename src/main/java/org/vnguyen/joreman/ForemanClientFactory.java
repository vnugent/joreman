package org.vnguyen.joreman;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class ForemanClientFactory {
	final static Logger logger = LoggerFactory.getLogger(ForemanClientFactory.class);
	
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
	
	public static ForemanAPI createAPI(String url, String userName, String password) {
		
		// parse given url and determine which port should be used
		URL parsedUrl;
		try {
			parsedUrl = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		int port = parsedUrl.getPort();
		if(port == -1){
			logger.debug("Port was omitted, using a default port for given protocol");
			port = parsedUrl.getDefaultPort();
		}
		
		logger.info("Creating a new proxy - host: {}, port: {}, protocol: {}, user: {}",
				parsedUrl.getHost(),port,parsedUrl.getProtocol(),userName);

		// Configure HttpClient to authenticate preemptively by prepopulating the authentication data cache.
		AuthCache authCache = new BasicAuthCache();
		 
		// Generate BASIC scheme object and add it to the local auth cache
		AuthScheme basicAuth = new BasicScheme();
		authCache.put(new HttpHost(parsedUrl.getHost(),port,parsedUrl.getProtocol()), basicAuth);
		 
		// Add AuthCache to the execution context
		BasicHttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		 
		// Create client executor and proxy
		ClientConnectionManager cm = new PoolingClientConnectionManager();
		// this is ugly hack to ignore certification validation
		if(parsedUrl.getProtocol().equalsIgnoreCase("https")){
			HTTPHelper.makeConnManagerTrustful(cm);
		}
		
		DefaultHttpClient httpClient = new DefaultHttpClient(cm);
	    httpClient.getCredentialsProvider().setCredentials(
	            org.apache.http.auth.AuthScope.ANY, new UsernamePasswordCredentials(userName,password));
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine( httpClient, localContext);
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		client.register(ClientErrorResponseFilter.class);
		client.register(JacksonJaxbJsonProvider.class);
		client.register(JacksonContextResolver.class);
        ResteasyWebTarget target = client.target(url);

        return target.proxy(ForemanAPI.class);
	}
	

	
	public ForemanClient createClient() {
		Config config = Config.load();
		return createClient(config);
	}
	
	public ForemanClient createClient(Config config) {
		logger.info("Creating a new foreman client with following configuration - url: {}, user: {}",
				config.foreman_url,config.foreman_user);
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
