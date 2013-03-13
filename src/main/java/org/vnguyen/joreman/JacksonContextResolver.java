package org.vnguyen.joreman;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Customize JSON processor
 *
 */

@Provider
@Produces("application/*+json")
@Consumes("application/*+json")
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper;

    public JacksonContextResolver() throws Exception {
        objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)	
        								  .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
        								  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        								  .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false)
        								  .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
        								  .setSerializationInclusion(Include.NON_NULL );
        								  
        
    }
    public ObjectMapper getContext(Class<?> objectType) {
        return objectMapper;
    }
}