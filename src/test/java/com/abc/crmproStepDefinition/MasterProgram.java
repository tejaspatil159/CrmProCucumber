package com.abc.crmproStepDefinition;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class MasterProgram {
	
	public static WebDriver driver;
	public static Actions act;
	
	@Before(order=0)
	public void launchTheSite() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("--disable-notifications");
		driver=new ChromeDriver(opt);
		act=new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://classic.freecrm.com");
		System.out.println("Site lauch successful");
		
	}
	
	@Before(order=1)
	public void loginToCrmpro() {
		WebElement username=driver.findElement(By.name("username"));
		username.sendKeys("tejaspatil159");
		WebElement pass=driver.findElement(By.name("password"));
		pass.sendKeys("Test@123");
		pass.submit();
		System.out.println("Login successful");
			
	}
	
	@After (order=0)
	public void logoutToCrmpro() {
		WebElement logout=driver.findElement(By.xpath("//a[@href='https://classic.freecrm.com/index.cfm?logout=1']"));
		logout.click();
		System.out.println("Logout successful");
	}
	
	@After(order=1)
	public void taskEnds() {
		System.out.println("Task ends");
		
	}
	
	@Given("^Verify that user is login successfully$")
	public void verify_that_user_is_login_successfully() throws Throwable {
		String tit=driver.getTitle();
		System.out.println("Title of current webpage is "+tit);
		Assert.assertEquals(tit,"CRMPRO");
		
	   
	}

	@When("^Mouse on contacts$")
	public void mouse_on_contacts() throws Throwable {
		driver.switchTo().frame("mainpanel");
		System.out.println("Frame is swiched");
		act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		System.out.println("Mouse on on contact is done");
	    
	}

	@When("^Click on new contact$")
	public void click_on_new_contact() throws Throwable {
		WebElement newContact=driver.findElement(By.xpath("//a[contains(text(),'New Contact')]"));
		newContact.click();
		Assert.assertEquals("First Name",driver.findElement(By.xpath("//td/strong[contains(text(),'First Name')]")).getText());
		System.out.println("Entered on new contact page");
		}

	@When("^Enter first name and Enter last name$")
	public void enter_first_name_and_Enter_last_name(DataTable contactDetails) {
	  for(Map<String, String> data: contactDetails.asMaps(String.class, String.class)) {
	   driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys(data.get("FirstName"));
	   driver.findElement(By.xpath("//input[@id='surname']")).sendKeys(data.get("LastName"));
	   driver.findElement(By.xpath("//input[@value='Save and Create Another (same company)']/preceding-sibling::input[@value='Save']")).click();
		act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Contacts')]"))).build().perform();
		WebElement newContact=driver.findElement(By.xpath("//a[contains(text(),'New Contact')]"));
		newContact.click();
	  }
	}

	

	@Then("^Verify that contacts add successfully$")
	public void verify_that_contacts_add_successfully() throws Throwable {
		Assert.assertEquals("First Name",driver.findElement(By.xpath("//td/strong[contains(text(),'First Name')]")).getText());
	   System.out.println("contact add successfully");
	}

	@When("^Mouse on deals$")
	public void mouse_on_deals() throws Throwable {
		driver.switchTo().frame("mainpanel");
		System.out.println("Frame is switched");
		act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Deals')]"))).build().perform();
		System.out.println("Mouse on on deals is done");
	    
	}

	@When("^Click on new deal$")
	public void click_on_new_deal() throws Throwable {
		WebElement newDeals=driver.findElement(By.xpath("//a[contains(text(),'New Deal')]"));
		newDeals.click();
		Assert.assertEquals("Title",driver.findElement(By.xpath("//strong[contains(text(),'Title')]")).getText());
		System.out.println("Entered on new deal page");
	   
	}

	@When("^Enter title and Enter amount and probability$")
	public void enter_title_and_Enter_amount_and_probability(DataTable dealData) throws Throwable {
		for(Map<String, String > data:dealData.asMaps(String.class,String.class)){
			
			driver.findElement(By.xpath("//input[@id='title']")).sendKeys(data.get("Title"));
			driver.findElement(By.xpath("//input[@name='amount']")).sendKeys(data.get("Amount"));
			driver.findElement(By.xpath("//input[@name='probability']")).sendKeys(data.get("probability"));
			driver.findElement(By.xpath("//input[@value='Save and Create Another']/preceding-sibling::input[@type='submit']")).click();
			act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Deals')]"))).build().perform();
			WebElement newDeals=driver.findElement(By.xpath("//a[contains(text(),'New Deal')]"));
			newDeals.click();		}
	   
	}

	@Then("^Verify that deals add successfully$")
	public void verify_that_deals_add_successfully() throws Throwable {
		Assert.assertEquals("Title",driver.findElement(By.xpath("//strong[contains(text(),'Title')]")).getText());
	    System.out.println("Deals added successfully");
	}



}
