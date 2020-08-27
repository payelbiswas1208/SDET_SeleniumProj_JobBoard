package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest1;

public class FindApplyJob extends BaseTest1 {

	@DataProvider(name = "JobSearch")
	public static Object[][] jobSearchData() {
		return new Object[][] { { "Python", "Python Faculty" } };
	}

	@Test(dataProvider = "JobSearch")
	public void applyJob(String jobSearch, String jobName) {

		/*
		 * Navigate to Jobs page
		 */
		WebElement jobLink = getDriver().findElement(By.linkText("Jobs"));
		jobLink.click();

		/*
		 * Verify that it's the Jobs page
		 */
		WebElement header = getDriver().findElement(By.cssSelector("header>h1"));
		String headerText = header.getText();
		Assert.assertEquals("Jobs", headerText, "Assert Header Text");

		/*
		 * Enter job keyword and click search job button
		 */
		WebElement searchJobField = getWaiter()
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id='search_keywords']")));
		searchJobField.sendKeys(jobSearch);

		WebElement searchJobBtn = getDriver().findElement(By.cssSelector("input[value='Search Jobs']"));
		searchJobBtn.click();

		/*
		 * Navigate to the required job and click Apply button
		 */
		String xFinder = String.format("//h3[text()='%s']", jobName);
		WebElement jobToApply = getWaiter().until(ExpectedConditions.presenceOfElementLocated(By.xpath(xFinder)));
		jobToApply.click();
		WebElement applyBtn = getWaiter()
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']")));
		applyBtn.click();

		/*
		 * Print the email to console
		 */
		WebElement jobApplyEmail = getWaiter()
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='job_application_email']")));
		System.out.println("Job Application Email is " + jobApplyEmail.getAttribute("innerHTML"));

	}
}
