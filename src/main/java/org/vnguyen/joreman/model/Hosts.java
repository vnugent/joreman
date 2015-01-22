package org.vnguyen.joreman.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hosts {
    @JsonProperty("results")
    public List<Host> hosts = null;
}
