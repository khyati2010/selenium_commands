package exercises;

import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import static org.hamcrest.core.Is.is;

/**
This program needs to be corrected. 
Frames Exercise Multi Window
**/

public class FramesExerciseMultWindow {
    static WebDriver driver;

    @BeforeClass
    public static void initializeFFDriver() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void closeFFDriver() {
        driver.quit();
    }

    @Before
    public void openTestURLFully() {
        driver.navigate().to("http://www.compendiumdev.co.uk/selenium/frames");
        // wait for correct title verification
        // before continuing
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
    }

    @Test
    public void switchToNewWindow() {

        assertEquals("Expected only 1 current window", 1,
                driver.getWindowHandles().size());

        String framesWindowHandle = driver.getWindowHandle();

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector(
                "a[href='http://www.seleniumsimplified.com']")).click();

        assertEquals("Expected a New Window opened", 2,
                driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle = "";

        for(String aHandle : myWindows) {
            if(!framesWindowHandle.contentEquals(aHandle)) {
                newWindowHandle = aHandle; break;
            }
        }

        driver.switchTo().window(newWindowHandle);

        assertTrue("Expected Selenium Simplified site",
                driver.getTitle().contains("Selenium Simplified"));

        // close new window
        driver.close();

        driver.switchTo().window(framesWindowHandle);
        // verify we're back at the frames window'
        assertEquals("Frameset Example Title (Example 6)", driver.getTitle());
        assertEquals("Expect 1 window open", 1, driver.getWindowHandles().size());
    }

    @Test
    public void accessPagesByName() {

        // get current window handle
        String framesetExampleWindow = driver.getWindowHandle();

        // switch to Content frame
        driver.switchTo().frame("content");

        // open eviltester window
        driver.findElement(By.cssSelector("a[id='goevil']")).click();
        // open Compendium Dev window
        driver.findElement(By.cssSelector("a[target='compdev']")).click();

        assertEquals("Expected 3 windows open", 3, driver.getWindowHandles().size());

        // switch to EvilTester window by name
        driver.switchTo().window("evil");
        // verify title
        assertThat(driver.getTitle(), is("Home | Evil Tester.com"));
        // close window
        driver.close();
        driver.findElement(By.className(""));
        // switch to Compendium Dev window by name
        driver.switchTo().window("compdev");
        // verify title
        assertThat(driver.getTitle(), is("Software Testing Essays, Book Reviews and Information"));
        // close window
        driver.close();

        // switch to first window
        driver.switchTo().window(framesetExampleWindow);
        // verify only 1 window open
        assertEquals("Expect 1 window open", 1, driver.getWindowHandles().size());
        // verify title
        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));
    }

	private void assertThat(String title, Matcher<String> matcher) {
		// TODO Auto-generated method stub
		
	}
}
