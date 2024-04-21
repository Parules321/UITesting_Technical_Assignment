package com.sauceDemo.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.sauceDemo.utility.Utility;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureReportListener extends TestListenerAdapter

{

	@Attachment(value = "{0}", type = "text")
	public void saveTextLog(String message) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(
				"Test Failed !!!!! Screenshot attached in Allure report " + result.getMethod().getMethodName());
		File screenshot = Utility.takeFailedTestScreenShot(result.getMethod().getMethodName());
		try {
			Allure.addAttachment(result.getMethod().getMethodName() + "Failed Test Screenshot",
					FileUtils.openInputStream(screenshot));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		saveTextLog("Screenshot of failed test");
	}

}