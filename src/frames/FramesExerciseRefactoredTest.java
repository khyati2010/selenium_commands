package frames;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


/**
* Frames Exercise Test 1 Refactored
**/

public class FramesExerciseRefactoredTest {
    private static WebDriver driver;
    private static String testURL = "http://www.compendiumdev.co.uk/selenium/frames/";
    private WebDriverWait wait = new WebDriverWait(driver,10);

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
        driver.navigate().to(testURL);
        // wait for correct title verification
        // before continuing
        wait.until(ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
    }

    @Test
    public void loadGreenPageAndBackAgain() {
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

        // assert we're on the Green Page
        WebElement greenTag = driver.findElement(By.cssSelector("#green"));
        assertThat(greenTag.getText(), is("Green Page"));

        driver.findElement(By.cssSelector("a[href='content.html']")).click();
        // study the following xpath to figure it out
        WebElement h1_content = wait.until(presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        // assert we're on the Content page again
        assertThat(h1_content.getText(), is("Content"));
    }
}
