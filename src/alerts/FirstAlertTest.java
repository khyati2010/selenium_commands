package alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * first exploration
 * alert test
 */
public class FirstAlertTest {
	static WebDriver driver;
	static String testURL = "http://www.compendiumdev.co.uk/selenium/alert.html";
	static String alertMessage = "I am an alert box!";

	@BeforeClass
	public static void startFFAndGetPage() {
		driver = new FirefoxDriver();
		driver.get(testURL);
	}
	@AfterClass
	public static void stopFF() {
		driver.quit();
	}

	@Test
	public void quickAlertTest() {
		WebElement alertButton;
		alertButton = driver.findElement(By.cssSelector("#alertexamples"));
		alertButton.click();
		
		assertEquals(alertMessage, driver.switchTo().alert().getText());
		// accept alert dialog to close
		driver.switchTo().alert().accept();
	}
	private void assertEquals(String alertMessage2, String text) {
		// TODO Auto-generated method stub
		
	}
}