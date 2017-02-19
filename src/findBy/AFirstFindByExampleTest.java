package findBy;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * First Find By Example - findById
*/
public class AFirstFindByExampleTest {

    static WebDriver driver;
    static Throwable trace;
    static Logger logger = Logger.getLogger("global");

    @BeforeClass
    public static void startFirefox() {
        driver = new FirefoxDriver();
        logger.setLevel(Level.WARNING);
    }

    @AfterClass
    public static void stopFirefox() {
        logger.info("stopping Firefox with close and quit");
        driver.close();
        driver.quit();
    }

    @Test
    public void findById() {
        logger.info("Navigating to test URL");
        driver.navigate().to("http://www.compendiumdev.co.uk" +
                "/selenium/find_by_playground.php");

        // verify correct page
        logger.info("Verifying page title");
        assertThat(driver.getTitle(), is("Welcome to the Find By Playground"));

        // get WebElement object info for b paragraph
        logger.info("Getting WebElement object for b paragraph");
        WebElement cParagraph = driver.findElement(By.id("p2"));

        // verify correct text for element
        logger.warning("Verifying paragraph text...highly flammable");
        assertThat(cParagraph.getText(), is("This is b paragraph text"));;

    }

	private void assertThat(String title, Object object) {
		// TODO Auto-generated method stub
		
	}

	private Object is(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}