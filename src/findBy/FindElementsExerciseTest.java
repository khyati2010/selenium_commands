package findBy;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindElementsExerciseTest {
    static WebDriver driver;

    @BeforeClass
    public static void startFirefox() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void stopFirefox() {
        driver.close();
        driver.quit();
    }

    @Before
    public void goToTestPage() {
        driver.get("http://www.compendiumdev.co.uk" +
            "/selenium/find_by_playground.php");
    }

    @Test
    public void assertDivElementCount() {
        List<WebElement> elements;
        elements = driver.findElements(By.tagName("div"));
        assertThat(elements.size(), is(19));
    }

    @Test
    public void assertAnchorTagCount() {
        List<WebElement> elements;
        elements = driver.findElements(By.partialLinkText("jump to para"));
        assertThat(elements.size(), is(25));
    }

    @Test
    public void assertParagraphCounts() {
        List<WebElement> elements;
        elements = driver.findElements(By.tagName("p"));
        assertThat("total para count", elements.size(), is(41));

        // now calculate nested paras

        int count = 0;
        for (WebElement x : elements) {
            if (x.getText().contains("nested para")) {
                count++;
            }
        }
        assertThat("total nested para count", count, is(16));

    }
}