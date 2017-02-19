package findBy;


import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;

/**
 *  First exercise using CSS search
**/

public class FindByCssFirstExerciseTest {
    static WebDriver driver;

    @BeforeClass
    public static void startFirefox() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void stopFirefox() {
        driver.close();
    }

    @Before
    public void navigateToPage() {
        driver.navigate().to("http://www.compendiumdev.co.uk/" +
                "selenium/find_by_playground.php");
    }

    @Test
    public void findByCssID() {
        WebElement element;
        element = driver.findElement(By.cssSelector("#p31"));
        assertThat(element.getAttribute("name"), is("pName31"));
    }

    private void assertThat(String attribute, Matcher<String> matcher) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void findByCSSName() {
        WebElement element;
        element = driver.findElement(By.cssSelector("[name=\"ulName1\"]"));
        assertThat(element.getAttribute("id"), is("ul1"));
    }

    @Test
    public void findByCSSClassName() {
        WebElement element;
        element = driver.findElement(By.cssSelector(".specialDiv"));
        assertThat(element.getAttribute("id"), is("div1"));
    }

    @Test
    public void findByCSSTagName() {
        WebElement element;
        element = driver.findElement(By.cssSelector("li"));
        assertThat(element.getAttribute("name"), is("liName1"));
    }
}