package org.vnguyen.joreman.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {

    @JsonProperty("results")
    public List<Image> images = null;
}
