package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest1;

public class CreateJobListing extends BaseTest1{
	
	@DataProvider(name = "JobInfoData")
	  public static Object[][] jobInfoData() {
	      return new Object[][] { {"wavasi8360@tmail7.com","COBOL Developer","Texas","Freelance",
	    	  "Description Dummy Text","Company Test","company@ymail.com"} };
	  }
	
  @Test(dataProvider = "JobInfoData")
  public void createJob(String email, String jobTitle, String location, String jobType, String desc,
		  String companyName, String appEmail) {
	  /*
	   * Navigate to Post a Job. verify page is loaded by checking preview button
	   */
	  WebElement postJobLink = getDriver().findElement(By.linkText("Post a Job"));
	  postJobLink.click();	  
	  WebElement preview = getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='submit_job']")));
	  
      /*
       * Enter new job details
       */
	  WebElement emailField = getDriver().findElement(By.id("create_account_email"));
	  emailField.sendKeys(email);
	  WebElement jobTitleField = getDriver().findElement(By.id("job_title"));
	  jobTitleField.sendKeys(jobTitle);
	  WebElement locationField = getDriver().findElement(By.id("job_location"));
	  locationField.sendKeys(location);
	  Select jobTypeField = new Select(getDriver().findElement(By.id("job_type")));
	  jobTypeField.selectByVisibleText(jobType);
	  WebElement appEmailField = getDriver().findElement(By.id("application"));
	  appEmailField.sendKeys(appEmail);
	  getDriver().switchTo().frame("job_description_ifr");
	  WebElement content = getDriver().findElement(By.id("tinymce"));
	  content.sendKeys(desc);
	  Assert.assertEquals(desc, content.getText(), "Assert text content - job desc field");	  
	  getDriver().switchTo().defaultContent();
	  WebElement companyField= getDriver().findElement(By.id("company_name"));
	  companyField.sendKeys(companyName);
	  preview.click();
	  WebElement submitListing = getWaiter().until(ExpectedConditions.visibilityOfElementLocated(By.id("job_preview_submit_button")));
	  submitListing.click();
	  
	  /*
	   * Go to Jobs page
	   */
	  WebElement jobLink = getDriver().findElement(By.linkText("Jobs"));
	  jobLink.click();	  
	  WebElement header = getDriver().findElement(By.cssSelector("header>h1"));
	  String headerText = header.getText();
	  Assert.assertEquals("Jobs", headerText, "Assert Header Text"); 	   
	 
	  /* 
	   * Verify added job is listed
	   */
	  WebElement searchJobField = getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.id("search_keywords"))); 
	  searchJobField.sendKeys(jobTitle);	  
	  WebElement searchJobBtn = getDriver().findElement(By.cssSelector("input[value='Search Jobs']"));
	  searchJobBtn.click();	  
	  String xFinder = String.format("//h3[text()='%s']", jobTitle);
	  WebElement myJobFound = getWaiter().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xFinder)));
	  Assert.assertEquals(jobTitle, myJobFound.getText() , "Assert job is found");
	
  }
}
