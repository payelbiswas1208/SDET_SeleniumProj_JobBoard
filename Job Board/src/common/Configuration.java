package common;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Configuration {
	public static String DRIVER_DIR = System.getProperty("user.dir") 
			+ File.separator
			+ "drivers"
			+ File.separator;
			
	public static String CHROME_DRIVER_PATH = DRIVER_DIR + "chromedriver";
	public static String GECKO_DRIVER_PATH = DRIVER_DIR + "geckodriver";
	
	public static String URL = "https://alchemy.hguy.co/jobs";
	public static String JOB_BOARD_URL = URL + "/";;
	public static String ADMIN_URL = JOB_BOARD_URL + "/wp-admin";
		
	public static String USER_NAME = "root";
	public static String PASSWORD = "pa$$w0rd";
	
	
	private static String modifyIfWindows(String inPath) {
		if (System.getProperty("os.name").toLowerCase().contains("windows")){
			return inPath + ".exe";
		} else {
			return inPath;
		}
	}
	
	public static WebDriver createChromeDriver(ChromeOptions options) {
		System.setProperty("webdriver.chrome.driver", modifyIfWindows(CHROME_DRIVER_PATH));
		return new ChromeDriver(options);
	}
	
	public static WebDriver createChromeDriver() {
		return createChromeDriver(new ChromeOptions());
	}
	
	public static WebDriver createFireFoxDriver() {
		System.setProperty("webdriver.gecko.driver", modifyIfWindows(GECKO_DRIVER_PATH));
		return new FirefoxDriver();
	}
}
