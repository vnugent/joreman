package org.vnguyen.joreman;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractGroup implements HostGroup {

	protected Map<String, HostParameter> params = new HashMap<String, HostParameter>();
	protected String groupId;
	
	public AbstractGroup(String groupId) {
		this.groupId = groupId;
	}
	
	public HostGroup addParam(String name, String value) {
		params.put(	Long.toString(System.currentTimeMillis()),
					new HostParameter(name, value));
		return this;
	}

	public String groupId() {
		return groupId;
	}
	
	public Map<String, HostParameter> params() {
		return params;
	}	
}
