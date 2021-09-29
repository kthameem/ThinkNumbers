package com.thinknumbers.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.thinknumbers.base.TestBase;

public class ListenerTest extends TestBase implements ITestListener {

	String testResult;
	
	
    @Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult Result) {					
    	if (!Result.isSuccess()) {
    		testResult = "Failed";
    	}
    	TestUtil.takeScreenshotOfFailedTest(testResult, Result.getMethod().getMethodName());
		System.out.println("The name of the testcase Failed is :"+Result.getName());		
    }		

    @Override		
    public void onTestSkipped(ITestResult Result) {					
    	System.out.println("The name of the testcase Skipped is :"+Result.getName());
        		
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult Result) {					
    	System.out.println("The name of the testcase Passed is :"+Result.getName());
        		
    }		
    
}