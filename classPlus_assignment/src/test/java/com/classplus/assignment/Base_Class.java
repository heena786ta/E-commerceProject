package com.classplus.assignment;



import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Utilities.BrowserOperation;


public class Base_Class extends BrowserOperation
{

	public  ExtentHtmlReporter htmlreporter;
	public static  ExtentReports extent;
	public static  ExtentTest test;

	@BeforeSuite
	/*
	 *  [TestMethod]
	 *  [Description("To create the Extent Report")] 
	 */
	public void extent_report() throws Exception {
		htmlreporter = new ExtentHtmlReporter("..//classPlus_assignment//ClassPlus.html");
		extent =  new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("SELENIUM", "Chrome");
		htmlreporter.config().setDocumentTitle("classPlus_Report");
		htmlreporter.config().setReportName("CLASSPLUS_ASSIGNMENT");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreporter.config().setTheme(Theme.STANDARD);
		BrowserOperation.openBrowser()	;


	}

	@BeforeMethod
	/*
	 *  [TestMethod]
	 *  [Description("To launch the site")] 
	 */
	public void open_browser() 
	{
		BrowserOperation.openApplication();
		
	}	



	@AfterMethod
	public void appium_server_stop(ITestResult result) throws Exception
	{
		if(result.getStatus()==ITestResult. FAILURE )
		{
			
			test.fail(MarkupHelper.createLabel(result.getName()+"TestCaseFailed", ExtentColor.RED));
			test.fail(result.getThrowable());

		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName()+"TestCasePasses", ExtentColor.GREEN));

		}
		else {
			test.skip(MarkupHelper.createLabel(result.getName()+"TestCaseSkipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
driver.manage().deleteAllCookies();
	}

	@AfterSuite
	/*
	 *  [TestMethod]
	 *  [Description("To close the report")] 
	 */
	public void flush_report(){
		driver.close();
		driver.quit();
		extent.flush();
	}

}
