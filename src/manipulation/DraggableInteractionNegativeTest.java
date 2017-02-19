package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Move draggable2 onto droppable1 assert text change to "Get Off Me!" utilize
 * User Interactions new Actions(driver)...perform, .build
 */

public class DraggableInteractionNegativeTest {

	static WebDriver driver;
	static String testURL = "http://www.compendiumdev.co.uk"
			+ "/selenium/gui_user_interactions.html";

	@BeforeClass
	public static void initializeAndStartFF() {
		driver = new FirefoxDriver();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setEnableNativeEvents(true);
		driver.get(testURL);
		// make sure page is fully loaded
		
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs("GUI User Interactions"));
	}

	@AfterClass
	public static void closeFF() {
		driver.quit();
	}

	@Test
	public void DraggableTest1() {
		WebElement draggable2 = driver.findElement(By
				.cssSelector("div#draggable2"));
		WebElement droppable1 = driver.findElement(By
				.cssSelector("div#droppable1"));

		// verify text initial state
		String droppable1Text = droppable1.getText();
		assertThat(droppable1Text, is("Drop here"));

		// now drag and drop, verify text change
		new Actions(driver).dragAndDrop(draggable2, droppable1).perform();
		droppable1Text = droppable1.getText();
		assertThat(droppable1Text, is("Get Off Me!"));
	}
}