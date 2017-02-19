package findBy;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;

/**
* some chaining experimentation
**/

public class ChainingTest {
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
    public void navigateToPage() {
        driver.get("http://www.compendiumdev.co.uk/selenium/find_by_playground.php");
    }

    @Test
    public void findByChaining() {
        WebElement element = driver.findElement(By.tagName("div")).
               findElement(By.id("p2")).
               findElement(By.id("a2"));

        assertEquals("expecting same name", "pName2", element.getAttribute("name"));
    }

    @Test
    public void findByByChained() {
        try {
            WebElement element = driver.findElement(
                    new ByChained(
                            By.id("div1"),
                            By.name("pName2"),
                            By.tagName("a")));

            assertEquals("expect different id", "a2", element.getAttribute("id"));
        } catch (NoSuchElementException e) {
            System.out.println("No such element:\n" + e);
            fail("caught exception");
        }
    }
}