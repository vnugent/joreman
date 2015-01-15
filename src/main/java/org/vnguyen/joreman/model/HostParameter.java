package org.vnguyen.joreman.model;

import com.fasterxml.jackson.annotation.JsonProperty;

	
public class HostParameter {
	@JsonProperty boolean _destroy = false;
	@JsonProperty String nested = "";
	@JsonProperty String name;
	@JsonProperty String value;
	@JsonProperty int reference_id=0;
	
	public HostParameter() {}
	
	public HostParameter(String name, String value) {
		this.name = name;
		this.value = value;
		//this.reference_id = reference_id;
	}
}
