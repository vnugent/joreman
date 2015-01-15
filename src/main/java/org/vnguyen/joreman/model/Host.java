package org.vnguyen.joreman.model;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.vnguyen.joreman.hostgroup.HostGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Host {
	@JsonProperty("name") 				public String name;
	@JsonProperty("ip")					public String ip;
	@JsonProperty("id")					public int id;
	@JsonProperty("environment_id") 	public String env="1";
	@JsonProperty("architecture_id") 	public int arch=1;
	@JsonProperty("operatingsystem_id") public int os=1;
	@JsonProperty("domain_id") 			String 	domain="1";
	@JsonProperty("subnet_id")          String  subnet="3";
	@JsonProperty("puppet_proxy_id") 	String puppet="1";
	@JsonProperty("compute_resource_id") String compute="2";
	@JsonProperty("hostgroup_id") 		public String hostGroup="4";
	@JsonProperty("provision_method") 	public String provisionMethod="build";
	@JsonProperty("build") 				String buildFlag="1";
	@JsonProperty("ptable_id") 			String ptableId="1";
	@JsonProperty("medium_id") 			String mediumId="6";
	@JsonProperty("image_id") 			public String imageId;
	@JsonProperty("is_owned_by")        public String isOwnedBy="18-Users";
	
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
