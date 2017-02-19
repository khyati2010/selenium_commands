package cookies;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * First Cookie Test
 */

public class FirstCookieTest {
	static WebDriver driver;
	static WebDriverWait wait;
	private WebElement queryBox;
	private WebElement queryButton;
	static final String testURL = "http://www.compendiumdev.co.uk/selenium/search.php";

	@BeforeClass
	public static void startChrome() {
		driver = new FirefoxDriver();
	//	driver = new ChromeDriver();
	}

	@AfterClass
	public static void closeFireFox() {
		driver.quit();
	}

	@Before
	public void openTestURL() {
		driver.navigate().to(testURL); // navigate to URl which is required.
		wait = new WebDriverWait(driver, 10); //Implicit wait appiled to driver
		driver.manage().deleteAllCookies();// delete all cookies which is in browser for fresh start
		refreshPageObjects();
	}

	private void refreshPageObjects() {
		queryBox = driver.findElement(By.cssSelector("input[title='Search']"));
		queryButton = driver.findElement(By.name("btnG"));
	}

	@Test
	public void searchAndCheckForCookies() {
		
		queryBox = driver.findElement(By.cssSelector("input[title='Search']"));
		queryButton = driver.findElement(By.name("btnG"));
		queryBox.clear();
		queryBox.sendKeys("monkey buns");
		queryButton.click();

		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie aCookie : cookies) {
			if (aCookie.getName().contentEquals(
					"seleniumSimplifiedSearchNumVisits")) {
				assertEquals("should be my first visit", "1",
						aCookie.getValue());
			}
			if (aCookie.getName().contentEquals(
					"seleniumSimplifiedSearchLastSearch")) {
				assertEquals("should equal 'monkey buns'", "monkey buns",
						aCookie.getValue());
			}
		}
	}

	@Test
	public void getOneSpecificCookie() {
		queryBox.clear();
		queryBox.sendKeys("monkey buns");
		queryButton.click();
		refreshPageObjects();
		queryBox.clear();
		queryBox.sendKeys("testing 123");
		queryButton.click();

		Cookie cookie = driver.manage().getCookieNamed(
				"seleniumSimplifiedLastSearch");
		// %20 is a space in the raw data that is returned
		assertEquals("should equal 'testing 123'", "testing%20123",
				cookie.getValue());
	}
}