package Utilities;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import config.Config;



public class BrowserOperation {

	public  static WebDriver driver;

	/**
	 * WebBrowser configuration setup.
	 * @throws Exception 
	 */
	public static WebDriver  openBrowser() throws Exception {
		switch (Config.TEST_BROWSER.trim().toUpperCase()){

		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", "path_to_geckodriver//geckodriver");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
			break;

		case "IE":

			driver = new InternetExplorerDriver();
			break;

		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "/Users/heena/OFR_AutomationSetup/BrowserDrivers/chromedriver");
			driver = new ChromeDriver();
			break;	

		default:
			throw new Exception("Incorrect browser set in config file.");
		}


		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * Application_Url open setup
	 */
	public static void openApplication(){
		driver.get(Config.APPLICATION_URL);
	//	driver.manage().window().maximize();
	}

	/**
	 * WebBrowser closing setup
	 */
	public static void closeBrowser(){
		driver.close();
		driver.quit();

	}



}


