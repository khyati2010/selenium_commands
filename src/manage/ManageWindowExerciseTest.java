package manage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Selenium;

import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Window 'manage' test
 **/

public class ManageWindowExerciseTest {
Selenium selenium;
	static WebDriver driver;
	// static String testURL =
	// "http://www.compendiumdev.co.uk/selenium/bounce.html";
	String testURL = "http://khyatisehgal.wordpress.com";
	static String winsize = "";
	static Dimension fullSize;

	@BeforeClass
	public static void startFF() {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void stopFF() {
		driver.quit();
	}

	@Before
	public void getTestURL() {
		driver.navigate().to(testURL);
		winsize = "";
	}

	@Test
	public void maximizeWindow() {
		driver.manage().window().maximize();
		winsize = driver.manage().window().getSize().toString();
		// assertThat(winsize, is("(1280, 742)"));
		assertThat(winsize, is("(1382, 702)"));
	}

	@Test
	public void halfSizeWindow() {
		// first maximize and verify
		driver.manage().window().maximize();
		String winsize = driver.manage().window().getSize().toString();
		// assertThat(winsize, is("(1280, 742)"));
		assertThat(winsize, is("(1382, 702)"));

		// now half size and verify
		// driver.manage().window().setSize(new Dimension(640, 371));
		driver.manage().window().setSize(new Dimension(691, 351));
		winsize = driver.manage().window().getSize().toString();
		assertThat(winsize, is("(691, 351)"));
	}

	@Test
	public void halfSizeWindowAlternate() {

		// first maximize and verify
		driver.manage().window().maximize();
		String winsize = driver.manage().window().getSize().toString();
		// assertThat(winsize, is("(1280, 742)"));
		assertThat(winsize, is("(1382, 702)"));

		// get full size dimension
		fullSize = driver.manage().window().getSize();

		// now re-size using fullSize / 2
		driver.manage()
				.window()
				.setSize(
						new Dimension(fullSize.getWidth() / 2, fullSize
								.getHeight() / 2));
		// now assert width and height using same
		assertThat(driver.manage().window().getSize().width,
				is(fullSize.getWidth() / 2));
		assertThat(driver.manage().window().getSize().height,
				is(fullSize.getHeight() / 2));
	}

	@Test
	public void moveToCenter() {
		// first make window half size and verify
		driver.manage().window().setSize(new Dimension(640, 371));
		winsize = driver.manage().window().getSize().toString();
		assertThat(winsize, is("(640, 371)"));
//driver.ge
		// now move window to center
		driver.manage().window().setPosition(new Point(300, 200));
	}

	@Test
	public void moveToCenterAlternate() {

		// maximize
		driver.manage().window().maximize();
		// get dimension to variable fullSize
		fullSize = driver.manage().window().getSize();

		// now create half size with fullSize / 2
		int newWidth = fullSize.getWidth() / 2;
		int newHeight = fullSize.getHeight() / 2;
		driver.manage().window().setSize(new Dimension(newWidth, newHeight));
		// and now position it, not sure why this formula (div by 4) works!
		driver.manage()
				.window()
				.setPosition(
						new Point(fullSize.getWidth() / 4,
								fullSize.getHeight() / 4));
	}

	@Test
	public void smallBounce() {
		// first make window half size and verify
		driver.manage().window().setSize(new Dimension(640, 371));
		winsize = driver.manage().window().getSize().toString();
		assertThat(winsize, is("(640, 371)"));

		for (int x = 0; x < 20; x++) {
			driver.manage().window().setPosition(new Point(0, 22));
			assertThat(getWinPos(), is("(0, 22)"));
			driver.manage().window().setPosition(new Point(800, 0));
			assertThat(getWinPos(), is("(800, 0)"));
			driver.manage().window().setPosition(new Point(800, 600));
			assertThat(getWinPos(), is("(800, 600)"));
			driver.manage().window().setPosition(new Point(0, 600));
			assertThat(getWinPos(), is("(0, 600)"));
		}
	}

	public String getWinPos() {
		String winpos = driver.manage().window().getPosition().toString();
		return winpos;
	}
}