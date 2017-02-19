package synchronization;


import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SynchronizationExerciseTest {
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
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void feelThePain() {
        driver.findElement(By.cssSelector("select[id='combo1'] option[value='3']")).click();

        wait.until(presenceOfElementLocated(By.cssSelector(
                "select[id='combo2'] option[value='23']")));

        /** some other wait options
*
* wait.until(ExpectedConditions.invisibilityOfElementLocated(
* By.id("ajaxBusy");
* RISK: might register success BEFORE 'busy' message comes up
*
* wait.until(ExpectedConditions.visibilityOfElementLocated(
* By.cssSelector("option[value='23]");
*
* wait.until(ExpectedConditions.elementToBeClickable(
* By.cssSelector("option[value='23]");
*
* wait.until(ExpectedConditions.titleIs("Processed Form Details");
*/
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("select[id='combo2'] option[value='23']")).click();
        driver.findElement(By.cssSelector("input[value='Code In It']")).click();
        String language_id = driver.findElement(By.cssSelector("li[id='_valuelanguage_id']")).getText();
        System.out.println(language_id);

        assertEquals("language_id should be 23", "23", language_id);
    }

    public void waitABit() {
        // this is only used for debugging
        try{
            Thread.currentThread().sleep(1000);
        }
        catch(InterruptedException ie){
        }
    }

}