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
	
	/**
	 * Append a random string to prefix
	 * @param prefix
	 * @return 
	 */
	public static String randomizeHostName(String prefix) {
		return randomizeHostName(prefix, 5);
	}
	
	/**
	 * Append a random string to prefix
	 * @param prefix
	 * @param length length of random string
	 * @return
	 */
	public static String randomizeHostName(String prefix, int length) {
		return prefix + "-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(length));

	}
}
