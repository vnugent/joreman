package org.vnguyen.joreman.hostgroup;

import java.util.Map;

import org.vnguyen.joreman.model.HostParameter;


public interface HostGroup {
	HostGroup addParam(String name, String value);
	String groupId();
	Map<String, HostParameter> params();
}
