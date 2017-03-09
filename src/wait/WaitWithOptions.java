package wait;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Wait with Options
 */

public class WaitWithOptions {
	static WebDriver driver;
	static WebDriverWait wait;
	static final String testURL = "http://www.compendiumdev.co.uk/selenium/basic_ajax.html";

	@BeforeClass
	public static void startFireFox() {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void closeFireFox() {
		driver.quit();
	}

	@Before
	public void openTestURL() {
		driver.navigate().to(testURL);

		driver.findElement(By.id("name"));
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void wait5Seconds() {
		long oldCurrentTime = System.currentTimeMillis();

		try {

			/* this will ignore the thrown exception in the apply */
			new WebDriverWait(driver, 1)
					.pollingEvery(100, TimeUnit.MILLISECONDS)
					.ignoring(IllegalStateException.class)
					.withTimeout(5, TimeUnit.SECONDS)
					.withMessage("A timeout happened as expected")
					.until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver webDriver) {
							throw new IllegalStateException();
						}
					});

			fail("a timeout exception should have been thrown");
		} catch (TimeoutException e) {
			assertTrue(e.getMessage()
					.contains("A timeout happened as expected"));
		}

		long newCurrentTime = System.currentTimeMillis();

		assertTrue((newCurrentTime - oldCurrentTime) > 5000); // more than 5
																// secs
	}

	/*
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * driver.get("http://somedomain/url_that_delays_loading"); WebElement
	 * myDynamicElement = driver.findElement(By.id("myDynamicElement"));
	 */
}