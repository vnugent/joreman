package org.vnguyen.joreman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Logs the response status and body for statuses != 200.
 * 
 * @author fbrychta
 *
 */
@Provider
public class ClientErrorResponseFilter implements ClientResponseFilter {
	final static Logger logger = LoggerFactory.getLogger(ClientErrorResponseFilter.class);

	@Override
	public void filter(ClientRequestContext requestContext,
			ClientResponseContext responseContext) throws IOException {
		if(responseContext.getStatus() != 200){
			StringBuilder errMsg = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(responseContext.getEntityStream()));
			String line;
			while((line = reader.readLine()) != null){
				errMsg.append(line);
			}
			logger.error("Response with error status {} was returned. Response body: {}.", 
					Integer.toString(responseContext.getStatus()), errMsg);
		}
	}
}
	