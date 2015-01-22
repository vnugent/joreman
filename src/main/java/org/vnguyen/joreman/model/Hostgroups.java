package org.vnguyen.joreman.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hostgroups {
    @JsonProperty("results")
    public List<HG> hostgroups = null;
}
