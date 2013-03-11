package org.vnguyen.joreman;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("host")
public class HostPower2 {
	@JsonProperty("name") 		public	String name;
	@JsonProperty("compute_attributes")	public			Compute_Attributes compAttrs;
	
	public static class Compute_Attributes {
		@JsonProperty String start;
		
		public Compute_Attributes(String start) { this.start = start; }
	}
	
	public static HostPower2 ON(String host) {
		HostPower2 power = new HostPower2();
		power.name = host;
		power.compAttrs = new Compute_Attributes("0");
		return power;
	}
}
