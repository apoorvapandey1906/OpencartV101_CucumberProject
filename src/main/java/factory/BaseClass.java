package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	static WebDriver driver;
	static Properties prop;
	static Logger logger;
	
	
	public static WebDriver initializeBrowser() throws IOException {
		prop = getProperties();
		String executionEnv = prop.getProperty("execution_env");
		String os = prop.getProperty("os");
		String browser = prop.getProperty("browser");
		
		if(executionEnv.equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch(os) {
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
				
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default :
				System.out.println("No matching OS");
				return null;
			}
			
			switch(browser) {
			case "chrome":
                capabilities.setBrowserName("chrome");
				break;
				
			case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
				break;
			
			case "firefox":
                capabilities.setBrowserName("firefox");
				break;
				
			default :
				System.out.println("No matching browser");
				return null;
			}
			
			driver = new RemoteWebDriver(new URL ("http://localhost:4444/wd/hub"),capabilities);
			
		} else if (executionEnv.equalsIgnoreCase("local")) {
			switch(browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				break;
			
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				break;
			default :
				System.out.println("No matching browser");
				driver= null;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		return driver;
	}
	
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Properties getProperties() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(file);
		return prop;
	}
	
	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}
	
	public static String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	
	public static String randomNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
		
	public static String randomAlphaNumeric()
	{
	String str=RandomStringUtils.randomAlphabetic(5);
	 String num=RandomStringUtils.randomNumeric(10);
	return str+num;
	}
	
	


}
