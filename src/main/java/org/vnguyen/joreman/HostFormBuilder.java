package org.vnguyen.joreman;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;




public class HostFormBuilder extends Host {
	public static final String DEFAULT_SIMPLE_HOST_JSON = "simple.host.json";

	/**
	 * Prepare a host form using data from a default json file
	 * @param hostName
	 * @return
	 * @throws Exception
	 */
	public static Host newTemplate(String hostName) throws Exception {
		return newTemplate(hostName, DEFAULT_SIMPLE_HOST_JSON);
	}
	
	public static Host newTemplate(String hostName, String jsonFile) throws Exception {
		Host host = JSONHelper.load(Host.class, jsonFile);
		host.name = hostName;
		return host;
	}
	
	public static String randomizeHostName(String prefix) {
		return prefix + "-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(8));
	}
}
