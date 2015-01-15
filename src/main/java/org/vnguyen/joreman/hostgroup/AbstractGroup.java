package org.vnguyen.joreman.hostgroup;

import java.util.HashMap;
import java.util.Map;

import org.vnguyen.joreman.model.HostParameter;


public abstract class AbstractGroup implements HostGroup {

	protected long time = System.currentTimeMillis();
	protected Map<String, HostParameter> params = new HashMap<String, HostParameter>();
	
	public HostGroup addParam(String name, String value) {
		// increment of timestamp to ensure uniqueness 
		params.put(	Long.toString(time++),
						new HostParameter(name, value));
		return this;
	}

	public Map<String, HostParameter> params() {
		prepareParams();
		return params;
	}


	protected void prepareParams() {};
}
