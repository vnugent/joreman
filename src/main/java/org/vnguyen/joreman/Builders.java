package org.vnguyen.joreman;

import java.util.HashMap;

import org.vnguyen.joreman.Compute_Attributes.Interfaces_Attributes;
import org.vnguyen.joreman.Compute_Attributes.Volumes_Attributes;



public class Builders {

	public static Compute_Attributes newComputeAttribute() {
		Compute_Attributes result = new Compute_Attributes();
		result.memory = "1073741824";
		result.cores = "2";
		result.start = "0";
		result.template = "00000000-0000-0000-0000-000000000000";
		result.cluster="fcb97476-3365-11e2-94d7-5254009cc188";
		
		result.interfaces_attributes = new HashMap<String, Interfaces_Attributes>(2);
		result.interfaces_attributes.put("new_interfaces", 
				new Interfaces_Attributes("00000000-0000-0000-0000-000000000009","", ""));
		result.interfaces_attributes.put("new_interfaces11", 
				new Interfaces_Attributes("00000000-0000-0000-0000-000000000009","eth0", ""));
		
		
		
		result.volumes_attributes = new HashMap<String, Volumes_Attributes>(2);
		result.volumes_attributes.put("new_volumes", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "", "", "")
				);
		result.volumes_attributes.put("new_volumes", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "", "", "")
				);
		result.volumes_attributes.put("new_volumes12", 
				new Volumes_Attributes("7d2f995a-4d6a-4897-b6e8-14cf6a82dee3", "8", "", "")
				);
				
		return result;
	}
	
	public static Host newHost(String name) {
		
		Host newHost = new Host();
		newHost.name = name;
		newHost.computeAttrs = newComputeAttribute();


		return newHost;
	}
		
	
}
