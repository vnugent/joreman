package org.vnguyen.joreman.filters;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This filter makes sure that foreman api version 2 will be used
 * @author fbrychta
 *
 */
public class AddVersionHeaderRequestFilter implements ClientRequestFilter{
    final static Logger logger = LoggerFactory.getLogger(AddVersionHeaderRequestFilter.class);
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // add header to define the api version
        requestContext.getHeaders().add("Accept", "version=2");
        
        // log all headers
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        Iterator<?> it = headers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<?, ?> pair = (Map.Entry<?, ?>)it.next();
            logger.debug("Header name: {}, value: {}.", pair.getKey(), pair.getValue()); 
        }
        
    }

}
