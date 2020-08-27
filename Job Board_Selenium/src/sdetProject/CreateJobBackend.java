package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest2;

public class CreateJobBackend extends BaseTest2 {

	@DataProvider(name = "JobInfo")
	public static Object[][] addJobInfo() {
		return new Object[][] { { "Jenkins Admin", "Toronto", "Company Test" } };
	}

	@Test(dataProvider = "JobInfo")
	public void addJobAdmin(String jobTitleText, String location, String companyName) {

		/*
		 * Go to Job Listings, click Add New
		 */
		getDriver().findElement(By.linkText("Job Listings")).click();
		getWaiter().until(ExpectedConditions.elementToBeClickable(By.id("doaction")));
		getDriver().findElement(By.linkText("Add New")).click();

		/*
		 * Enter job details
		 */
		WebElement position = getDriver().findElement(By.cssSelector("textarea[id]"));
		position.sendKeys(jobTitleText);
		WebElement locationField = getDriver().findElement(By.cssSelector("input[name='_job_location']"));
		locationField.sendKeys(location);
		WebElement companyNameField = getDriver().findElement(By.cssSelector("input[name='_company_name']"));
		companyNameField.sendKeys(companyName);

		/*
		 * Click on Publish and View Job
		 */
		WebElement publishBtn = getDriver().findElement(By.cssSelector("button[class*='publish-button']"));
		publishBtn.click();
		getDriver().findElement(By.xpath("//button[text()='Publish']")).click();
		WebElement viewJob = getWaiter().until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Job")));
		viewJob.click();
		/*
		 * Navigate to Jobs page and search for job created
		 */
		getDriver().findElement(By.linkText("Jobs")).click();
    	getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='search_keywords']")))
				.sendKeys(jobTitleText);
		getDriver().findElement(By.cssSelector("input[value='Search Jobs']")).click();
		String xFinder = String.format("//h3[text()='%s']", jobTitleText);
		getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xFinder))).click();;
		
		WebElement applyBtn = getWaiter()
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']")));
		Assert.assertTrue(applyBtn.isEnabled(), "Assert apply button enabled");

	}

}
