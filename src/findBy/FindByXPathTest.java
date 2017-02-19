package findBy;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Find By XPath Test
*/
public class FindByXPathTest {
    static WebDriver driver;

    @BeforeClass
    public static void startFFAndGotoPage() {
        driver = new FirefoxDriver();
        driver.navigate().to("http://compendiumdev.co.uk" +
            "/selenium/find_by_playground.php");
    }

    @AfterClass
    public static void stopFF() {
        driver.quit();
    }

    @Test
    public void assertByID() {
        WebElement element;

        // findElement(By...
        element = driver.findElement(By.id("p31"));
        assertThat(element.getAttribute("name"), is("pName31"));

        // findElement(By.css...
        element = driver.findElement(By.cssSelector("#p31"));
        assertThat(element.getAttribute("name"), is ("pName31"));

        // findElement(By.xpath...
        element = driver.findElement(By.xpath("//p[@id='p31']"));
        assertThat(element.getAttribute("name"), is ("pName31"));
    }

    @Test
    public void assertByName() {
        WebElement element;

        // findElement(By...
        element = driver.findElement(By.name("ulName1"));
        assertThat(element.getAttribute("id"), is("ul1"));

        // findElement(By.css...
        element = driver.findElement(By.cssSelector("[name=ulName1]"));
        assertThat(element.getAttribute("id"), is ("ul1"));

        // findElement(By.xpath...
        element = driver.findElement(By.xpath("//*[@name='ulName1']"));
        assertThat(element.getAttribute("id"), is ("ul1"));
    }

    @Test
    public void assertByClassName() {
        WebElement element;

        // findElement(By...
        element = driver.findElement(By.className("specialDiv"));
        assertThat(element.getAttribute("id"), is("div1"));

        // findElement(By.css...
        element = driver.findElement(By.cssSelector(".specialDiv"));
        assertThat(element.getAttribute("id"), is ("div1"));

        // findElement(By.xpath...
        element = driver.findElement(By.xpath("//*[@class='specialDiv']"));
        assertThat(element.getAttribute("id"), is ("div1"));
    }

    @Test
    public void assertByTagName() {
        WebElement element;

        // findElement(By...
        element = driver.findElement(By.tagName("li"));
        assertThat(element.getAttribute("name"), is("liName1"));

        // findElement(By.css...
        element = driver.findElement(By.cssSelector("li"));
        assertThat(element.getAttribute("name"), is ("liName1"));

        // findElement(By.xpath...
        element = driver.findElement(By.xpath("//li"));
        assertThat(element.getAttribute("name"), is ("liName1"));
    }
}