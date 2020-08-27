package sdetProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Configuration;

public class WebsiteBackend {
	@Test
	public void loginAdmin() {

		/*
		 * Launch browser and navigate to url
		 */
		WebDriver driver = Configuration.createChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get(Configuration.ADMIN_URL);

		/*
		 * Enter username and verify text
		 */
		WebElement userTextBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("log")));
		userTextBox.sendKeys(Configuration.USER_NAME);
		Assert.assertEquals(Configuration.USER_NAME, userTextBox.getAttribute("value"), "Assert the user name text");

		/*
		 * Enter password and verify text
		 */
		WebElement pwdTextBox = driver.findElement(By.name("pwd"));
		pwdTextBox.sendKeys(Configuration.PASSWORD);
		Assert.assertEquals(Configuration.PASSWORD, pwdTextBox.getAttribute("value"), "Assert the password text");

		/*
		 * Click submit button and verify homepage is displayed
		 */
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("wp-submit")));
		submitButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='WordPress Events and News']")));

		/*
		 * Logout and quit browser
		 */
		WebElement logOut = driver.findElement(By.xpath("//*[text()='Log Out']"));
		driver.get(logOut.getAttribute("href"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'logged out')]")));
		driver.quit();
	}
}
