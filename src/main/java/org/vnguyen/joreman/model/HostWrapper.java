package org.vnguyen.joreman.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HostWrapper {
	@JsonProperty("host")
	private Host host;

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

}
