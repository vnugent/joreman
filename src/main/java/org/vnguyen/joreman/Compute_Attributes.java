package org.vnguyen.joreman;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Compute_Attributes {
	@JsonProperty String cores="1";
	@JsonProperty String cluster;
	@JsonProperty String memory;
	@JsonProperty String template;
	@JsonProperty String start;
	
	@JsonProperty Map<String, Interfaces_Attributes> interfaces_attributes;
	@JsonProperty Map<String, Volumes_Attributes> volumes_attributes;
	
	public static class Interfaces_Attributes {
		public String network;
		public String name;
		public String _delete;
		
		public Interfaces_Attributes() {}
		
		public Interfaces_Attributes(String network, String name, String _delete) {
			this.network = network;
			this.name = name;
			this._delete = _delete;
		}
		
	}
	
	public static class Volumes_Attributes {
		public String storage_domain;
		public String size_gb;
		public String id;
		public String _delete;
		
		public Volumes_Attributes() {}
		
		public Volumes_Attributes(String storage_domain, String size_gb, String id, String _delete) {
			this.storage_domain = storage_domain;
			this.size_gb = size_gb;
			this.id = id;
			this._delete = _delete;
		}
	}
		
}
