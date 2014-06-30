package org.vnguyen.joreman;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
	@JsonProperty("medium_id") 			String mediumId="6";
	@JsonProperty("image_id") 			String imageId;
	
	@JsonProperty("mac")				public String mac;
	
	@JsonProperty("compute_attributes") 			public Compute_Attributes computeAttrs;
	
	@JsonProperty("host_parameters_attributes") 	public Map<String, HostParameter> hostParams;


	public Host withHostGroup (HostGroup app) {
		hostParams = app.params();
		hostGroup = app.groupId();
		return this;
	}
	
	@Override
	public String toString() {
		return  new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append(name)
					.append(ip)
					.append(hostGroup)
					.toString();
	}
}
