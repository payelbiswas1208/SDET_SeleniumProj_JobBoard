package sdetProject;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest1;

public class VerifyPageTitle extends BaseTest1 {

	@Test
	public void verifyTitle() {
		/*
		 * Verify page title
		 */
		System.out.println("Title of the page is : " + getDriver().getTitle());
		String titlePage = getDriver().getTitle();
		Assert.assertEquals("Alchemy Jobs – Job Board Application", titlePage, "Assert page title");

	}
}
