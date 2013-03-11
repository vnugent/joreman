package org.vnguyen.joreman;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("host")
public class Host {
	@JsonProperty("name") 				String name;
	@JsonProperty("ip")					String ip;
	@JsonProperty("environment_id") 	String env="1";
	@JsonProperty("architecture_id") 	String arch="1";
	@JsonProperty("operatingsystem_id") String os="1";
	@JsonProperty("domain_id") 			String 	domain="1";
	@JsonProperty("puppet_proxy_id") 	String puppet="1";
	@JsonProperty("compute_resource_id") String compute="2";
	@JsonProperty("hostgroup_id") 		String hostGroup="2";
	@JsonProperty("provision_method") 	String provisionMethod="build";
	@JsonProperty("build") 				String buildFlag="1";
	@JsonProperty("ptable_id") 			String ptableId="1";
	
	@JsonProperty("compute_attributes") Compute_Attributes computeAttrs;
	
	@JsonProperty("host_parameters") 	List<HostParameter> hostParameters;
}
