package org.vnguyen.joreman;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractGroup implements HostGroup {

	protected Map<String, HostParameter> params = new HashMap<String, HostParameter>();
	
	public HostGroup addParam(String name, String value) {
		params.put(	Long.toString(System.currentTimeMillis()),
					new HostParameter(name, value));
		return this;
	}

	public Map<String, HostParameter> params() {
		return params;
	}
	
}
