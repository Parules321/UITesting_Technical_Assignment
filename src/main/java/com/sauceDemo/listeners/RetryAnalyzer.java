package com.sauceDemo.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.sauceDemo.testBase.TestBase;
public class RetryAnalyzer extends TestBase implements IRetryAnalyzer {
	int cnt = 0;
	int maxCnt = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (cnt < maxCnt) {
			logger.info("Test failed, Retrying for " + cnt + " ,Test Name : " + result.getMethod().getMethodName());
			cnt++;
			return true;
		}
		return false;
	}

}
