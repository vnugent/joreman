package org.vnguyen.joreman;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractGroup implements HostGroup {

	protected List<ParamWrapper> params = new ArrayList<ParamWrapper>();
	protected String groupId;
	
	public AbstractGroup(String groupId) {
		this.groupId = groupId;
	}
	
	public HostGroup addParam(String name, String value) {
		params.add(new ParamWrapper(new HostParameter(name, value)));
		return this;
	}

	public String groupId() {
		return groupId;
	}
	
	public List<ParamWrapper> params() {
		return params;
	}
	
}
