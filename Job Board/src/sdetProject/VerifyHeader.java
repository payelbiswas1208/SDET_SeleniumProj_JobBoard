package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest1;

public class VerifyHeader extends BaseTest1 {

	@Test
	public void verifyHeading_2() {

		/*
		 * Verify Heading 2(h2) in Home page
		 */

		WebElement heading2 = getDriver().findElement(By.cssSelector("h2"));
		String headingText2 = heading2.getText();
		Assert.assertEquals("Quia quis non", headingText2, "Assert Header2 text");

	}

}
