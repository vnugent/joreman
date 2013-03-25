package org.vnguyen.joreman;


public class ExampleHostGroup extends AbstractGroup {

	/**
	 * return the associating foreman host_group id
	 */
	public String groupId() {
		return "4";
	}

	@Override
	protected void prepareParams() {
		addParam("name", "jackbauer");
		addParam("code", "topsecret");
	}

}
