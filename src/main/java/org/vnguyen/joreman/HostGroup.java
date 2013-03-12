package org.vnguyen.joreman;

import java.util.List;


public interface HostGroup {
	HostGroup addParam(String name, String value);
	String groupId();
	List<ParamWrapper> params();
}
