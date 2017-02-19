package alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*import org.junit.AfterClass;
 import org.junit.Before;
 import org.junit.BeforeClass;
 import org.junit.Test;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;*/
/*
 import static junit.framework.Assert.assertEquals;
 import static org.hamcrest.core.Is.is;
 import static org.junit.Assert.assertThat;
 */
/**
 * alerts exercise test
 */
public class AlertsExerciseTest {
	static WebDriver driver;
	static String testURL = "http://www.compendiumdev.co.uk/selenium/alerts.html";
	static String alertMessage = "I am an alert box!";
	static String confirmAlertMessage = "I am a confirm alert";
	static String promptAlertMessage = "I prompt you";
	static String promptText = "text is changed";

	@BeforeClass
	public static void startFF() {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void stopFF() {
		driver.quit();
	}

	@BeforeClass
	public void refreshPageAndVerifyLoad() {
		driver.get(testURL);
		// wait for page load
		new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs(""));

		/*
		 * titleIs is a method in ExpectedConditions which has further other
		 * methods.
		 */
	}

	@Test
	public void plainAlertTest() {
		WebElement alertButton;
		alertButton = driver.findElement(By.cssSelector("#alertexamples"));
		alertButton.click();
		assertThat(alertMessage, driver.switchTo().alert().getText());
		// accept alert dialog to close
		driver.switchTo().alert().accept();
	}

	@Test
	public void confirmAlertTestOK() {
		WebElement confirmAlertButton;
		confirmAlertButton = driver.findElement(By
				.cssSelector("#confirmexample"));
		confirmAlertButton.click();
		assertThat((driver.switchTo().alert().getText()),
				is(confirmAlertMessage));
		// accept alert dialog to close via 'OK'
		driver.switchTo().alert().accept();
		// verify page text is 'true'
		String returnText = driver
				.findElement(By.cssSelector("#confirmreturn")).getText();
		assertThat(returnText, is("true"));
	}

	@Test
	public void confirmAlertTestCancel() {
		WebElement confirmAlertButton;
		confirmAlertButton = driver.findElement(By
				.cssSelector("#confirmexample"));
		confirmAlertButton.click();
		assertThat((driver.switchTo().alert().getText()),
				is(confirmAlertMessage));
		// accept alert dialog to close via 'OK'
		driver.switchTo().alert().dismiss();
		// verify page text is 'false'
		String returnText = driver
				.findElement(By.cssSelector("#confirmreturn")).getText();
		assertThat(returnText, is("false"));
	}

	@Test
	public void promptAlertTestOK() {
		WebElement confirmAlertButton;
		confirmAlertButton = driver.findElement(By
				.cssSelector("#promptexample"));
		confirmAlertButton.click();
		// verify prompt alert text
		assertThat((driver.switchTo().alert().getText()),
				is(promptAlertMessage));
		// change message
		driver.switchTo().alert().sendKeys(promptText);
		/*
		 * accept alert dialog to close via 'OK' how sendKeys help in closing OK
		 * button, why we simply dont click on click or press enter from
		 * keyboard.
		 */
		driver.switchTo().alert().accept();
		// verify return text is (promptText)
		String returnText = driver.findElement(By.cssSelector("#promptreturn"))
				.getText();
		assertThat(returnText, is(promptText));
	}

	@Test
	public void promptAlertTestCancel() {
		WebElement confirmAlertButton;
		confirmAlertButton = driver.findElement(By
				.cssSelector("#promptexample"));
		confirmAlertButton.click();
		// verify prompt alert text
		assertThat((driver.switchTo().alert().getText()),
				is(promptAlertMessage));
		// change message
		driver.switchTo().alert().sendKeys(promptText);
		// REJECT alert dialog to close via 'Cancel'
		driver.switchTo().alert().dismiss();
		// verify return text is default "pret"
		String returnText = driver.findElement(By.cssSelector("#promptreturn"))
				.getText();
		assertThat(returnText, is("pret"));
	}

	private void assertThat(String string, Object object) {
		// TODO Auto-generated method stub

	}

	private Object is(String promptAlertMessage2) {
		// TODO Auto-generated method stub
		return null;
	}
}
