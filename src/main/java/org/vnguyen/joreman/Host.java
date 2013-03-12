package org.vnguyen.joreman;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("host")
public class Host {
	@JsonProperty("name") 				public String name;
	@JsonProperty("ip")					public String ip;
	@JsonProperty("id")					public int id;
	@JsonProperty("environment_id") 	public String env="1";
	@JsonProperty("architecture_id") 	String arch="1";
	@JsonProperty("operatingsystem_id") String os="1";
	@JsonProperty("domain_id") 			String 	domain="1";
	@JsonProperty("puppet_proxy_id") 	String puppet="1";
	@JsonProperty("compute_resource_id") String compute="2";
	@JsonProperty("hostgroup_id") 		public String hostGroup="4";
	@JsonProperty("provision_method") 	String provisionMethod="build";
	@JsonProperty("build") 				String buildFlag="1";
	@JsonProperty("ptable_id") 			String ptableId="1";
	
	@JsonProperty("compute_attributes") public Compute_Attributes computeAttrs;
	
	@JsonProperty("host_parameters_attributes") 	public List<ParamWrapper> hostParams;


}
