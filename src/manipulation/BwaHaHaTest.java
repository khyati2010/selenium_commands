package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
* Pressing Ctrl + B keys 
*/
public class BwaHaHaTest {
    static WebDriver driver;
    static String testURL = "http://www.compendiumdev.co.uk" +
            "/selenium/gui_user_interactions.html";

    @BeforeClass
    public static void initializeAndStartFF() {
        driver = new FirefoxDriver();
        //FirefoxProfile profile = new FirefoxProfile();
        //profile.setEnableNativeEvents(true);
        driver.get(testURL);
        // make sure page is fully loaded
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.titleIs("GUI User Interactions"));
    }

    @AfterClass
    public static void closeFF() {
        driver.quit();
    }

    @Test
    public void doControlBActionForTextChange() {
        WebElement draggable1 = driver.findElement(By.cssSelector("div#draggable1"));

        // verify text initial state
        String draggable1Text = draggable1.getText();
        //assertThat(draggable1Text, is("Drag me"));

        // this gets the page in focus
        // needed for firefox
        driver.findElement(By.tagName("html")).click();

        Actions actions = new Actions(driver);

        new Actions(driver).keyDown(Keys.CONTROL).
                sendKeys("b").
                keyUp(Keys.CONTROL).
                perform();

        draggable1Text = draggable1.getText();
        assertThat(draggable1Text, is("Bwa! Ha! Ha!"));
    }
}