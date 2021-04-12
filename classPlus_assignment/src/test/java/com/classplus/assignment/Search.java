
	package com.classplus.assignment;

	import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
	import org.testng.Assert;
	import org.testng.annotations.Test;
	import com.aventstack.extentreports.Status;
	import pageObjects.Search_Page;

	public class Search extends Base_Class 
	{
		public static WebElement AmazonPrice;
		public static WebElement FlipkartPrice;
		public static int AmazonAmount;
		public static int FlipkartAmount;
		
			@Test
			/*
			*  [TestMethod]
			*  [Description("To compare the price of two e-commerce site")] 
			*/
			public static void SearchPhone() throws InterruptedException
			{
				test = extent.createTest("Search_iPhone");
				
				test.log(Status.INFO, "Launching the amazon site");	

				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				WebElement Logo=driver.findElement(By.xpath(Search_Page.Amazon_Logo));
				
				if(Logo.isDisplayed())
				{
					System.out.println("Site Successfully launched");
					test.log(Status.PASS, "Verfying the Amazon Logo ");
				}		
				else
				{
					System.out.println("Failed to Verify the logo");
					test.log(Status.FAIL, "Failed to verify the Amazon Logo");
				}		

				WebElement SearchBox=driver.findElement(By.xpath(Search_Page.SearchTextBox));
				SearchBox.sendKeys("iPhone XR (64GB) - Yellow");
				SearchBox.sendKeys(Keys.ENTER);
				
				
				test.log(Status.INFO, "Searching iPhone XR (64GB) - Yellow ");	
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				List<WebElement>MobileList=driver.findElements(By.xpath("//div/h2/a/span[@class='a-size-medium a-color-base a-text-normal']"));
				System.out.println();
				
				if(MobileList.size()>0)
				{
					if(MobileList.get(0).getText().contains("iPhone XR"))
					{
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						
						test.log(Status.PASS, "Succesfully found the result");
						System.out.println("Succesfully found the result");
						MobileList.get(0).click();
						ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					    driver.switchTo().window(tabs2.get(1));
					    AmazonPrice=driver.findElement(By.xpath("//tr[@id='priceblock_ourprice_row']/td/span[@id='priceblock_ourprice']"));
					   
					    String price=AmazonPrice.getText();
					    System.out.println("devicePrice"+  price);
					    String s[]=price.split("\\s");
					    String str=s[1];
					   // AmazonAmount=Integer.parseInt(str);
						
					}
					else
					{
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						
						test.log(Status.FAIL, "No matching result found,selecting other device");
						System.out.println("No matching result found,selecting other device");
						MobileList.get(0).click();
						ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					    driver.switchTo().window(tabs2.get(1));
					    AmazonPrice=driver.findElement(By.xpath("//tr[@id='priceblock_ourprice_row']/td/span[@id='priceblock_ourprice']"));
					    String price=AmazonPrice.getText();
					    System.out.println("devicePrice"+  price);
					    String s[]=price.split("\\s");
					    String str=s[1];
					    System.out.println(str);
					  //  AmazonAmount=Integer.parseInt(str);  	
					}
					
				}
				else
				{
					test.log(Status.FAIL, "No  result found,selecting other device");
					System.out.println("No  result found,selecting other device");
				}
				
				driver.navigate().to("https://www.flipkart.com/");
				
				test.log(Status.INFO, "Launching the flipkart site");
				System.out.println("Launching the flipkart site");
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

				driver.findElement(By.className("_2doB4z")).click();
				
				WebElement FlipkartSearchBox=driver.findElement(By.name(Search_Page.FlipkartSearchBox));
			
				FlipkartSearchBox.sendKeys("iPhone XR (64GB) - Yellow");
				FlipkartSearchBox.sendKeys(Keys.ENTER);
				FlipkartSearchBox.sendKeys(Keys.ENTER);
				
				test.log(Status.INFO, "Searching iPhone XR (64GB) - Yellow ");	
				
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				
				List<WebElement>MobileList1=driver.findElements(By.className("_4rR01T"));
				System.out.println(MobileList1.size());
				
				
				if(MobileList1.size()>0)
				{
					if(MobileList1.get(0).getText().contains("iPhone XR"))
					{
						test.log(Status.PASS, "Succesfully found the result");
						System.out.println("Succesfully found the result");
						MobileList1.get(0).click();
						ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					    driver.switchTo().window(tabs2.get(2));
					    FlipkartPrice=driver.findElement(By.className("_16Jk6d"));
					    
					    String price=FlipkartPrice.getText();
					    System.out.println("devicePrice"+  price);
					    String s[]=price.split("₹");
					    String str=s[1];
					    System.out.println(str);
					   // FlipkartAmount=Integer.parseInt(str); 
					    
						
					}
					else
					{
						test.log(Status.FAIL, "No matching result found,selecting other device");
						System.out.println("No matching result found,selecting other device");
						MobileList1.get(0).click();
						ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					    driver.switchTo().window(tabs2.get(2));
					    FlipkartPrice=driver.findElement(By.className("_16Jk6d"));
					    String price=FlipkartPrice.getText();
					    System.out.println("devicePrice"+  price);
					    String s[]=price.split("₹");
					    String str=s[1];
					    System.out.println(str);
					   // FlipkartAmount=Integer.parseInt(str); 	
					}
					
				}
				else
				{
					test.log(Status.FAIL, "No  result found,selecting other device");
					System.out.println("No  result found,selecting other device");
				}

				if(AmazonAmount<FlipkartAmount)
				{
					System.out.println("Iphone is cheaper at amazon with a price of "+AmazonAmount);
				}
				else
				{
					System.out.println("Iphone is cheaper at Flipkart with a price of "+FlipkartAmount);
				}
				
		}

	}



