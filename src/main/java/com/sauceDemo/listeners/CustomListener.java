package com.sauceDemo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sauceDemo.testBase.TestBase;
import com.sauceDemo.utility.Utility;

public class CustomListener extends TestBase implements ITestListener {

	public void onStart(ITestContext context) {
		logger.info("Test suite started: " + context.getName() + "\nNumber of test methods in this suite: "
				+ context.getAllTestMethods().length);
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Starts : " + result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed : " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		logger.info("Test Failed !!!!! Taking Screenshot : " + result.getMethod().getMethodName());
		
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("Test Skipped : " + result.getMethod().getMethodName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onFinish(ITestContext context) {
		context.getPassedTests().getAllResults().stream().map(ITestResult::getName) // Map ITestResult to test names
				.forEach(testName -> System.out.println("PASSED - " + testName));

		context.getFailedTests().getAllResults().stream().map(ITestResult::getName)
				.forEach(testName -> System.out.println("FAILED - " + testName));

		context.getSkippedTests().getAllResults().stream().map(ITestResult::getName)
				.forEach(testName -> System.out.println("SKIPPED - " + testName));

	}

}
