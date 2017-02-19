package customExpectedConditions;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class InlineCustomExTest {
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
    public void tryTheNewConditionMethod() {
        driver.findElement(By.cssSelector("select[id='combo1'] option[value='3']")).click();

        //wait.until(ExpectedConditions.optionw // <--- stuck here as to how to call method

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

    // this is copied from Udemy
    private ExpectedCondition<WebElement> optionWithValueDisplayed(final String value) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector(
                        "option[value='" + value + "']"));
            }
        }; // this semicolon was on the slide, need to research why it's needed
    }

}