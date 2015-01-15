package org.vnguyen.joreman.hostgroup;


/**
 * A simple host group based on Foreman group id
 *
 */
public class FixedGroup extends AbstractGroup {
	protected String groupId;
	
	public FixedGroup(String groupId) {
		this.groupId = groupId;
	}

	public String groupId() {
		return groupId;
	}

}
