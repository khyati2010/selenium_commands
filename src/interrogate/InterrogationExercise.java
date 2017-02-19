package interrogate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

//import static org.junit.Assert.assertThat;

/**
* Interrogation Exercise
*/

public class InterrogationExercise {

    static WebDriver driver;

    @BeforeClass
    public static void startDriver() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void stopDriver() {
        driver.close();
    }

    @Test
    public void performBasicInterrogationExercise() {
        String testSiteURL = "http://www.compendiumdev.co.uk/selenium/basic_web_page.html";

        driver.navigate().to(testSiteURL);
///assertEquals( )
        // confirm web page title
        assertThat(driver.getTitle(), is("Basic Web Page Title"));
        // confirm site url
        assertThat(driver.getCurrentUrl(), is(testSiteURL));
        // confirm page source content
        assertThat(driver.getPageSource(), containsString("A paragraph of text"));
    }
}