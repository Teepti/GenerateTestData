package com.testdata.api.generatedata;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Data {
	

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println(args[0]);
		
		String home = System.getProperty("user.home");
		String filePath = home+"/Downloads/";
		//System.setProperty("webdriver.chrome.driver","C://Users//Revaan//Downloads//chromedriver_win32//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",args[0]);
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://localhost/generatedata-3.4.1/generatedata-3.4.1/");
		driver.findElementByXPath("//li[@id='gdLoadLink']").click();
		driver.switchTo().activeElement();
		driver.findElementByXPath("//*[@id='gdMainDialogTab2Content']//table[@id='gdAccountDataSets']//tr[@data-id='3']//td[7]").click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement ele =driver.findElementByXPath("//div[@id='gdGenerateSection']//button[@id='gdGenerateButton']");
		wait.until(ExpectedConditions.visibilityOf(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		ele.click();
		Thread.sleep(3000);
		driver.close();
		File file = getLastModified(filePath);
		System.out.println(file.getName());
		String newFilePath = args[1];
		file.renameTo(new File(newFilePath));
		System.out.println("file renamed successfully");
		
		
		
	}
	
	public static File getLastModified(String directoryFilePath)
	{
	    File directory = new File(directoryFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    {
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}

}
