package org.vnguyen.joreman;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hostgroup")
public class HG {
	@JsonProperty("name") 				public String name;
	@JsonProperty("id") 				public Integer id;
	@JsonProperty("subnet_id") 			public Integer subnetId;
	@JsonProperty("operatingsystem_id") public Integer operatingSystemId;
	@JsonProperty("domain_id") 			public Integer domainId;
	@JsonProperty("environment_id") 	public Integer environmentId;
	@JsonProperty("ancestry") 			public String ancestry;
	@JsonProperty("label") 				public String label;
	
	@JsonProperty("parameters") 	public Map<String, String> Params;
	@JsonProperty("puppetclass_ids") 	public Integer[] puppetclassIds;
	

}
