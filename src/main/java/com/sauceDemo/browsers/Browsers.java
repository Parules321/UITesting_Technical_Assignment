package com.sauceDemo.browsers;

public enum Browsers {
	CHROME("chrome"),
	EDGE("edge"),
	FIREFOX("firefox");
	
	
	private String browserName;

	Browsers(String browser) {
		this.browserName=browser;
	}

	public String getBrowserName() {
		return browserName;
	}	
}