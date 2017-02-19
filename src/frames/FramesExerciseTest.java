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


/**
*
* Frames Exercise Test 1
*
*/
public class FramesExerciseTest {
    static WebDriver driver;
    static String testURL = "http://www.compendiumdev.co.uk/selenium/frames/";

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
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));
    }

    @Test
    public void loadGreenPageAndBackAgain() {
        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='green.html']")).click();
        new WebDriverWait(driver, 10).until
                (ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1[id='green']")));

        // assert we're on the Green Page
        WebElement greenTag = driver.findElement(By.cssSelector("#green"));
        assertThat(greenTag.getText(), is("Green Page"));

        driver.findElement(By.cssSelector("a[href='content.html']")).click();
        // study the following xpath to figure it out
        new WebDriverWait(driver, 10).until
                (ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[.='Content']")));

        // assert we're on the Content page again
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Content"));
    }

    @Test
    public void iframePageTest() {
        driver.switchTo().frame("menu");
        driver.findElement(By.cssSelector("a[href='iframe.html']")).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("HTML Frames Example - iFrame Contents"));
        assertThat(driver.findElement(By.cssSelector("h4")).getText(), is("Iframe Below"));
        driver.switchTo().frame(0);
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Menu 1"));

        driver.findElement(By.cssSelector("a[href='frames_example_5.html']")).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("Frameset Example Title (Example 5)"));

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='index.html']")).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("Frameset Example Title (Example 6)"));

    }
}