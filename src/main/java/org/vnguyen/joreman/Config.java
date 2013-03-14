package org.vnguyen.joreman;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "config")
public class Config {
	@JsonProperty String foreman_url;
	@JsonProperty String foreman_user;
	@JsonProperty String foreman_password;
	
	public static Config load() {
		String configFile = System.getProperty("joreman.config");

		if (configFile == null) {
			configFile = "/default.config.json"; 
		}

		return JSONHelper.load(Config.class, configFile);

	}
}
