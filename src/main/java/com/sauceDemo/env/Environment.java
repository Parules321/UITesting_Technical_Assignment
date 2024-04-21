package com.sauceDemo.env;

public enum Environment {
	DEV("https://www.saucedemodev.com/"),
	STAGE("https://www.saucedemostage.com/"),
	PROD("https://www.saucedemo.com/");

	private String envUrl;

	Environment(String envUrl) {
		this.envUrl = envUrl;
	}

	public String getEnvUrl() {
		return envUrl;
	}

}