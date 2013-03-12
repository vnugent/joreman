package org.vnguyen.joreman;

import com.fasterxml.jackson.annotation.JsonProperty;

	
public class HostParameter {
	@JsonProperty String name;
	@JsonProperty String value;
	//@JsonProperty int reference_id;
	
	public HostParameter() {}
	
	public HostParameter(String name, String value) {
		this.name = name;
		this.value = value;
		//this.reference_id = reference_id;
	}
}
