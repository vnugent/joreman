package org.vnguyen.joreman.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty
    public String id;
    @JsonProperty
    public String name;
    @JsonProperty("operatingsystem_id")
    public int operatingSystemId;
    @JsonProperty("architecture_id")
    public int archId;
    @JsonProperty
    public String uuid;
    
    public String toString(){
        return "id: " +id+ ", name: " +name;
        
    }
}
