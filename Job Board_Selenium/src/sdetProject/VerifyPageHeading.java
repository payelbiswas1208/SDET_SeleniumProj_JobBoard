package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest1;

public class VerifyPageHeading extends BaseTest1 {
	@Test
	public void verifyHeading() {

		/*
		 * Verify page heading in Home page
		 */
		WebElement heading = getDriver().findElement(By.cssSelector("h1[class='entry-title']"));
		String headingText = heading.getText();
		Assert.assertEquals("Welcome to Alchemy Jobs", headingText, "Assert Heading text");

	}
}
