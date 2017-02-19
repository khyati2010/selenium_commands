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

import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;

/**
* FindBy exercise
*/

public class FindByExerciseTest {
    static WebDriver driver;
    static final String PROTOCOL = "http";
    static final String DOMAIN = "www.compendiumdev.co.uk";
    static final String WEBPAGE = "/selenium/find_by_playground.php";
    static final String TEST_URL = PROTOCOL + "://" + DOMAIN + WEBPAGE;
    static WebElement element;
    static Logger logger = Logger.getLogger("global");

    // Before and After statements

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
    public void navigateToTestUrl() {
        driver.navigate().to(TEST_URL);
    }

    // Testing begins

    @Test
    public void findElementByID() {
        element = driver.findElement(By.id("p5"));

        assertThat(element.getText(), is("This is e paragraph text"));
        assertThat(element.getAttribute("id"), is("p5"));
        assertThat(element.getAttribute("name"), is("pName5"));
        assertThat(element.getAttribute("class"), is("normal"));
    }

    private void assertThat(String text, Matcher<String> matcher) {
		// TODO Auto-generated method stub
		
	}

	@Test
    public void findElementByLinkText() {
        element = driver.findElement(By.linkText("jump to para 0"));

        assertThat(element.getText(), is("jump to para 0"));
        assertThat(element.getAttribute("id"), is("a26"));
        assertThat(element.getAttribute("name"), is("aName26"));
        assertThat(element.getAttribute("class"), is("normal"));
    }

    @Test
    public void findElementByName() {
        element = driver.findElement(By.name("pName32"));

        assertThat(element.getText(), is("nested para text"));
        assertThat(element.getAttribute("id"), is("p32"));
        assertThat(element.getAttribute("name"), is("pName32"));
        assertThat(element.getAttribute("class"), is("normal"));
    }

    @Test
    public void findElementByPartialLinkText() {
        element = driver.findElement(By.partialLinkText("para 16"));

        assertThat(element.getText(), is("jump to para 16"));
        assertThat(element.getAttribute("id"), is("a42"));
        assertThat(element.getAttribute("name"), is("aName42"));
        assertThat(element.getAttribute("class"), is("normal"));
    }

    @Test
    public void findElementByClassName() {
        element = driver.findElement(By.className("linkDiv"));

        assertThat(element.getText(), containsString("jump to para"));
        assertThat(element.getAttribute("id"), is("div18"));
        assertThat(element.getAttribute("name"), is("linkdivname"));
        assertThat(element.getAttribute("class"), is("linkDiv"));
    }

	private Matcher<String> containsString(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}