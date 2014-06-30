package org.vnguyen.joreman;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HGWrapper {
	@JsonProperty("hostgroup")	
	private HG hostGroup;

	public HG getHostGroup() {
		return hostGroup;
	}

	public void setHostGroup(HG hostGroup) {
		this.hostGroup = hostGroup;
	}

	
}
