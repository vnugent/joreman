package org.vnguyen.joreman;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Provider
@Produces("application/*+json")
@Consumes("application/*+json")
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
    private ObjectMapper objectMapper;

    public JacksonContextResolver() throws Exception {
        objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)	
        								  .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
        								  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
    }
    public ObjectMapper getContext(Class<?> objectType) {
        return objectMapper;
    }
}