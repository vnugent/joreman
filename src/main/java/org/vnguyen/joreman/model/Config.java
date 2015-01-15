package org.vnguyen.joreman.model;

import org.vnguyen.joreman.util.JSONHelper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
	@JsonProperty public String foreman_url;
	@JsonProperty public String foreman_user;
	@JsonProperty public String foreman_password;
	
	public Config() {}
	
	public Config(String url, String user, String password) {
		this.foreman_url = url;
		this.foreman_user = user;
		this.foreman_password = password;
	}

	public static Config load() {
		String configFile = System.getProperty("joreman.config");

		if (configFile == null) {
			configFile = "/default.config.json"; 
		}

		return JSONHelper.load(Config.class, configFile);

	}
}
