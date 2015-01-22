package org.vnguyen.joreman.model;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HG {
	@JsonProperty("name") 				public String name;
	@JsonProperty("id") 				public Integer id;
	@JsonProperty("subnet_id") 			public Integer subnetId;
	@JsonProperty("operatingsystem_id") public Integer operatingSystemId;
	@JsonProperty("domain_id") 			public Integer domainId;
	@JsonProperty("environment_id") 	public Integer environmentId;
	@JsonProperty("ancestry") 			public String ancestry;
	@JsonProperty("title") 				public String title;
	
	@JsonProperty("parameters") 	public ArrayList<Map<String, String>> Params;
	@JsonProperty("puppetclass_ids") 	public Integer[] puppetclassIds;
	

}
