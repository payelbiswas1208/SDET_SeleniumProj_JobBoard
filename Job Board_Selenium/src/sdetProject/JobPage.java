package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest1;

public class JobPage extends BaseTest1 {
	@Test
	public void jobPage() {

		/*
		 * Navigate to Job page and verify header text
		 */
		WebElement jobLink = getDriver().findElement(By.linkText("Jobs"));
		jobLink.click();
		WebElement header = getDriver().findElement(By.cssSelector("header>h1"));
		String headerText = header.getText();
		Assert.assertEquals("Jobs", headerText, "Assert Header Text");

	}
}
