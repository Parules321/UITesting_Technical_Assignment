package com.sauceDemo.testBase;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.sauceDemo.browsers.Browsers;
import com.sauceDemo.env.Environment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Logger logger;
	private static String defaultBrowser;
	private static String defaultEnv;

	@BeforeSuite
	public void setUpBeforeSuite() {

		AllureEnvironmentWriter
				.allureEnvironmentWriter(
						ImmutableMap.<String, String>builder().put("Browser", getDefaultBrowser())
								.put("URL", getDefaultEnv()).put("OS", System.getProperty("os.name"))
								.put("OS Version", System.getProperty("os.version")).build(),
						System.getProperty("user.dir") + "/allure-results/");

		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
	}

	public void initialization() {
		setDriverForTesting();
		driverManagement();
		driver.get(getDefaultEnv());
	}

	private static String getDefaultEnv() {
		defaultEnv = Environment.PROD.getEnvUrl();
		return defaultEnv;
	}

	public static String getDefaultBrowser() {
		defaultBrowser = Browsers.CHROME.getBrowserName();
		return defaultBrowser;

	}

	private void driverManagement() {
		driver.manage().window().maximize();
	}

	private void setDriverForTesting() {
		switch (getDefaultBrowser()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser");

		}

	}

	public void tearDown() {
		driver.close();
	}

}
