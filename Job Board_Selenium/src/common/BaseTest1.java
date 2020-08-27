package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest1 {

	private WebDriver driver = null;
	private WebDriverWait wait = null;

	@BeforeClass
	public void launchBrowser() {

		setDriver(Configuration.createChromeDriver());
		wait = new WebDriverWait(getDriver(), 60);
		getDriver().get(Configuration.JOB_BOARD_URL);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void closeBrowser() {
		getDriver().quit();
	}

	protected WebDriver getDriver() {
		return this.driver;
	}

	private void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected WebDriverWait getWaiter() {
		return this.wait;
	}
}
