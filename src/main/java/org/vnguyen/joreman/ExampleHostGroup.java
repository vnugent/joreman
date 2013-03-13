package org.vnguyen.joreman;

public class ExampleHostGroup extends AbstractGroup {

	public ExampleHostGroup(String foo) {
		addParam("my_key", "some value");
		addParam("name", foo);
	}
	
	/**
	 * return the associating foreman host_group id
	 */
	public String groupId() {
		return "4";
	}

}
