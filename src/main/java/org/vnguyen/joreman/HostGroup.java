package org.vnguyen.joreman;

import java.util.Map;


public interface HostGroup {
	HostGroup addParam(String name, String value);
	String groupId();
	Map<String, HostParameter> params();
}
