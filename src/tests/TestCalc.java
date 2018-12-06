package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 
public class TestCalc {
	
	 

public static void main(String[] args) {
    
	// objects and variables instantiation
	    	   System.setProperty("webdriver.gecko.driver", "/home/michel/Downloads/selenium/geckodriver");
	              WebDriver driver = new FirefoxDriver();
	
	             // String url = "http://soblinux01.otago.ac.nz:8090/bcclient/calculator.html";
	              String url = "http://localhost:5389/bcclient/calculator.html";
	              
	              
	              driver.get(url);
	              WebElement eth = driver.findElement(By.id("eth"));
	              WebElement age1 = driver.findElement(By.id("age1"));
	              WebElement age2 = driver.findElement(By.id("age2"));
	              WebElement par  = driver.findElement(By.id("par"));
	              WebElement con  = driver.findElement(By.id("con"));
	              
	              WebElement hbcan = driver.findElement(By.id("hbcan"));
	              WebElement hbben = driver.findElement(By.id("hbben"));
	              WebElement htdis = driver.findElement(By.id("htdis"));
	              WebElement age = driver.findElement(By.id("age"));
	              
	              WebElement button = driver.findElement(By.id("button"));
	              
	              
	              
	              
	              eth.sendKeys("1");
	              age1.sendKeys("1");
	              age2.sendKeys("1");
	              par.sendKeys("0");
	              con.sendKeys("0");
	              hbcan.sendKeys("0");
	              hbben.sendKeys("0");
	              htdis.sendKeys("1");
	              age.sendKeys("30");
	              
	              button.click();
	              
	              WebDriverWait wait = new WebDriverWait(driver, 35); 
	              
	              WebElement risk = wait.until( ExpectedConditions.visibilityOfElementLocated(By.id("riskValue")) );
	              
	              
	              String message = risk.getAttribute("rv");
	              System.out.println(message);
	            		  
	 
	              driver.quit();	 

	}
}


