package com.thinknumbers.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyser implements IRetryAnalyzer{
	int count = 0;
	int limit = 2;

	public boolean retry(ITestResult result) {
		if (count < limit) {
			count++;
			return true;
		}
		return false;
	}
}
