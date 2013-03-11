package org.vnguyen.joreman;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("host")
public class HostPower {
	@JsonProperty public String name;
	@JsonProperty public String poweron="0";
}
