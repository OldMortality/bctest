package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class TestAll {

	public static String getVisibleText(String element, String value) {
		String result = "";
		if (element.equals("eth")) {
			if (value.equals("eur"))
				result = "non-Maori";
			if (value.equals("mao"))
				result = "Maori";

		}

		if (element.equals("age1")) {
			if (value.equals("1"))
				result = "15+";
			if (value.equals("2"))
				result = "12-14";
			if (value.equals("3"))
				result = "less than 12";

		}
		if (element.equals("age2")) {
			if (value.equals("1"))
				result = "50+";
			if (value.equals("2"))
				result = "45-59";
			if (value.equals("3"))
				result = "less than 45";
			if (value.equals("4"))
				result = "pre-menopause";

		}
		if (element.equals("par")) {
			if (value.equals("0"))
				result = "none";
			if (value.equals("1"))
				result = "1 child";
			if (value.equals("2"))
				result = "2 children";
			if (value.equals("3"))
				result = "3 children";
			if (value.equals("4"))
				result = "4+ children";
		}
		if (element.equals("con")) {
			if (value.equals("0"))
				result = "Never";
			if (value.equals("1"))
				result = "Yes";
		}
		if (element.equals("hbcan")) {
			if (value.equals("0"))
				result = "No";
			if (value.equals("1"))
				result = "Second degree only";
			if (value.equals("2"))
				result = "First degree";
		}
		if (element.equals("hbben")) {
			if (value.equals("0"))
				result = "No";
			if (value.equals("1"))
				result = "Yes";
		}
		if (element.equals("htdis")) {
			if (value.equals("0"))
				result = "No";
			if (value.equals("1"))
				result = "Yes";
		}
		if (element.equals("age")) {
			result = value;
		}
		return (result);
	}

	public static void testAll(String url, int justOne, WebDriver driver, String fname) {

		if (justOne > 0) {
			System.out.println("JUST RUNNING ONE TEST!");
		}

		// System.setProperty("webdriver.gecko.driver",
		// "/home/michel/Downloads/selenium/geckodriver");
		/// WebDriver driver = new FirefoxDriver();

		// String csvFile = "/home/michel/Documents/breast cancer calculator/" + fname;
		// //testdata.csv";
		String csvFile = fname;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int counter = -1;
		int countGood = 0;
		int countBad = 0;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (counter == -1) {
					// skip the header
					counter++;
					continue;
				}

				counter++;
				boolean ok = true;
				if (justOne <= 0 || counter == justOne) {
					System.out.println("");
					System.out.println("");
					System.out.println("---------------");
					System.out.println("Running test case " + counter);
					// use comma as separator
					String[] country = line.split(cvsSplitBy);
					String eth = country[0];
					String age1 = country[1];
					String age2 = country[2];
					String par = country[3];
					String con = country[4];
					String hbcan = country[5];
					String hbben = country[6];
					String htdis = country[7];
					String age = country[8];
					String rr = country[9];

					ok = testOne(url, driver, eth, age1, age2, par, con, hbcan, hbben, htdis, age, rr);
					System.out.println("Test " + counter + " was " + ok);
				}
				if (ok) {
					if (justOne <= 0) {
						countGood++;
					}

				} else {
					countBad++;
					System.out.println("This test was not good. " + counter);

				}

			}
			if (justOne <= 0) {
				System.out.println("test file: " + fname);
				System.out.println("tested this many " + counter);
				System.out.println("This many were good:\t" + countGood);
			} else {

			}
			System.out.println("This many were bad:\t" + countBad);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// driver.quit();
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (justOne > 0) {
			System.out.println("JUST RAN ONE TEST!");
		}

	}

	/*
	 * gets the index as displayed on the web interface
	 * 
	 * These methods tell you where a value is found on the select list. So for eth,
	 * value "eur" is the second option in the select. The index starts at zero for
	 * the top row.
	 * 
	 * 
	 */
	public static int getWebIndexEth(String eth) {
		int result = -1;

		if (eth.equals("mao")) {
			result = 0;
		}
		if (eth.equals("eur")) {
			result = 1;
		}

		return result;
	}

	// these are in reverse on the screen
	public static int getWebIndexAge1(String age1) {
		int result = 0;

		if (age1.equals("1")) {
			result = 2;
		}
		if (age1.equals("2")) {
			result = 1;
		}
		if (age1.equals("3")) {
			result = 0;
		}
		return result;
	}

	// age of menopause
	public static int getWebIndexAge2(String age2) {
		int result = 0;

		// 50+
		if (age2.equals("1")) {
			result = 3; // bottom of the list
		}
		// 45-49
		if (age2.equals("2")) {
			result = 2;
		}
		// < 45
		if (age2.equals("3")) {
			result = 1;
		}
		// pre-menopause
		if (age2.equals("4")) {
			result = 0; // top of the list
		}
		return result;
	}

	// parity
	public static int getWebIndexParity(String parity) {
		int result = -1;
		if (parity.equals("0"))
			result = 0;
		if (parity.equals("1"))
			result = 1;
		if (parity.equals("2"))
			result = 2;
		if (parity.equals("3"))
			result = 3;
		if (parity.equals("4"))
			result = 4;
		return (result);
	}

	// no yes variables
	public static int getWebIndexNoYes(String ny) {
		int result = -1;
		if (ny.equals("0"))
			result = 0;
		if (ny.equals("1"))
			result = 1;
		return (result);
	}

	// no yes variables
	public static int getWebIndexHbcan(String hb) {
		int result = -1;
		// no
		if (hb.equals("0"))
			result = 0;
		// 2nd degree
		if (hb.equals("1"))
			result = 1;
		// 1st degree
		if (hb.equals("2"))
			result = 2;
		return (result);
	}

	public static boolean testOne(String url, WebDriver driver, String _eth, String _age1, String _age2, String _par,
			String _con, String _hbcan, String _hbben, String _htdis, String _age, String _rr) {

		System.out.println(_eth + " " + _age1 + " " + _age2 + " " + _par + " " + _con + " " + _hbcan + " " + _hbben
				+ " " + _htdis + " " + _age);

		driver.get(url);

		Select eth = new Select(driver.findElement(By.id("eth")));
		Select age1 = new Select(driver.findElement(By.id("age1")));
		Select age2 = new Select(driver.findElement(By.id("age2")));
		Select par = new Select(driver.findElement(By.id("par")));
		Select con = new Select(driver.findElement(By.id("con")));

		Select hbcan = new Select(driver.findElement(By.id("hbcan")));
		Select hbben = new Select(driver.findElement(By.id("hbben")));
		Select htdis = new Select(driver.findElement(By.id("htdis")));
		Select age = new Select(driver.findElement(By.id("age")));

		// agree with the disclaimer
		WebElement toc = driver.findElement(By.id("toc"));
		toc.click();
		// wait for the button to appear
		WebDriverWait wait1 = new WebDriverWait(driver, 35);

		WebElement button = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("button")));

		// WebElement button = driver.findElement(By.id("button"));

		int webEth = getWebIndexEth(_eth);
		// System.out.println("Ethnicity " + webEth);
		eth.selectByIndex(webEth);

		// age1.selectByVisibleText(getVisibleText("age1", _age1));
		age1.selectByIndex(getWebIndexAge1(_age1));

		age2.selectByIndex(getWebIndexAge2(_age2));
		par.selectByIndex(getWebIndexParity(_par));
		con.selectByIndex(getWebIndexNoYes(_con));
		hbcan.selectByIndex(getWebIndexHbcan(_hbcan));
		hbben.selectByIndex(getWebIndexNoYes(_hbben));
		htdis.selectByIndex(getWebIndexNoYes(_htdis));
		age.selectByVisibleText(getVisibleText("age", _age));

		boolean ok = false;
		if ((_age2.equals("2") && new Integer(_age) < 45) || (_age2.equals("1") && new Integer(_age) < 50)) {

			System.out.println("invalid age/age2 combination");

			WebDriverWait waiterr = new WebDriverWait(driver, 35);

			// wait until description is visible
			WebElement ageText = waiterr.until(ExpectedConditions.visibilityOfElementLocated(By.id("ageText")));

			String errMsg = ageText.getAttribute("value");

			System.out.println("ersMsg" + errMsg);

			if ("Age at menopause is inconsistent with current age.".equals(errMsg)) {
				ok = true;
			} else {
				ok = false;
			}

		} else {

			button.click();

			WebDriverWait wait = new WebDriverWait(driver, 35);

			// wait until description is visible
			WebElement risk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("riskDescription")));
			// retrieve absolute risk, it is an attribute of the hidden riskValue
			WebElement absrisk = driver.findElement(By.id("riskValue"));

			ok = false;
			String received = absrisk.getAttribute("rv");
			float receivedF = new Float(received);
			float expectedF = new Float(_rr);

			if (Math.abs(receivedF - expectedF) < 0.00015) {
				System.out.println("expected " + expectedF + " and received: " + receivedF);
				System.out.println("ok");
				ok = true;
			} else {
				System.out.println("expected " + _rr + " received " + received);
			}

			// test category in the risk description
			String riskText = risk.getAttribute("value");
			String LOW_EXP = "low risk";
			String MOD_EXP = "moderate risk";
			String HIG_EXP = "high risk";
			String VHI_EXP = "very high risk";

			if (receivedF < 0.0035) {
				if (riskText.indexOf(LOW_EXP) == -1) {
					System.out.println("error: expected " + LOW_EXP);
					ok = false;
				} else {
					System.out.println("risk category correctly described as: " + LOW_EXP);

				}
			} else {
				if (receivedF < 0.008) {
					if (riskText.indexOf(MOD_EXP) == -1) {
						System.out.println("error: expected " + MOD_EXP);
						ok = false;
					} else {
						System.out.println("risk category correctly described as: " + MOD_EXP);

					}
				} else {
					if (receivedF < 0.013) {
						if (riskText.indexOf(HIG_EXP) == -1) {
							System.out.println("error: expected " + HIG_EXP);
							ok = false;
						} else {
							System.out.println("risk category correctly described as: " + HIG_EXP);

						}
					} else {
						if (riskText.indexOf(VHI_EXP) == -1) {
							System.out.println("error: expected " + VHI_EXP);
							ok = false;
						} else {
							System.out.println("risk category correctly described as: " + VHI_EXP);

						}

					}
				}
			}
		}

		return ok;

	}

	/**
	 * main test program. I export this to runnnable jar and run as java -jar
	 * testall.jar url filename
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// will run all lines
		// testAll(-1);
		// will just run line 4
		System.setProperty("webdriver.gecko.driver", "/home/michel/Downloads/selenium/geckodriver");
		WebDriver driver = new FirefoxDriver();

		// MJ file:

		// String fname = "/home/michel/Documents/breast cancer
		// calculator/testdata/testdata.csv";
		// from rstudio:
		// String fname = "/home/michel/Documents/breast cancer
		// calculator/testdata/mytest01.csv";

		String fname = args[0];
		String url = args[1];

		// String url = "http://soblinux01.otago.ac.nz:8090/bcclient/calculator.html";
		// String url = "http://localhost:5389/bcclient/calculator.html";

		// -1 will run all tests. testAll(4,driver,fname) will just run line 4
		testAll(url, -1, driver, fname);
		driver.close();
		System.out.println("done");

	}

}
