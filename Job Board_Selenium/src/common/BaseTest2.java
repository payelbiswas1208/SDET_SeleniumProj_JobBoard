package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest2 {

	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@BeforeClass
	public void launchBrowser() {

		setDriver(Configuration.createChromeDriver());
		wait = new WebDriverWait(getDriver(), 90);
		driver.get(Configuration.ADMIN_URL);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void loginMethod() {

		WebElement userTextBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("log")));
		userTextBox.sendKeys(Configuration.USER_NAME);
		Assert.assertEquals(Configuration.USER_NAME, userTextBox.getAttribute("value"), "Assert the user name text");

		WebElement pwdTextBox = driver.findElement(By.name("pwd"));
		pwdTextBox.sendKeys(Configuration.PASSWORD);
		Assert.assertEquals(Configuration.PASSWORD, pwdTextBox.getAttribute("value"), "Assert the password text");

		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("wp-submit")));
		submitButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='WordPress Events and News']")));
	}

	@AfterMethod
	public void logoutMethod() {

		WebElement logOut = driver.findElement(By.xpath("//*[text()='Log Out']"));
		driver.get(logOut.getAttribute("href"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'logged out')]")));
		driver.quit();
	}

	@AfterClass
	public void closeBrowser() {

		driver.quit();
	}

	protected WebDriver getDriver() {
		return driver;
	}

	private void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected WebDriverWait getWaiter() {
		return this.wait;
	}

}
