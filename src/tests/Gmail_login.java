package tests;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

	public class Gmail_login {
	/**
	* @param args
	*/
	       public static void main(String[] args) {
	             
	// objects and variables instantiation
	    	   System.setProperty("webdriver.gecko.driver", "/home/michel/Downloads/selenium/geckodriver");
	              WebDriver driver = new FirefoxDriver();
	
	              String appUrl = "http://www.google.com";
	              driver.get(appUrl);
	             
	 
	              driver.quit();	       }
	}