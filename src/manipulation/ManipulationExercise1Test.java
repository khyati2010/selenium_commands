package manipulation;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * 
 * Manipulation Exercise 1 Test:
 * 
 * Using http://compendiumdev.co.uk/selenium/basic_html_form.html
 * 
 * Submit form and assert page title changes Clear, then type comments, submit
 * form and check output Submit form with radio 2 selected Submit form with only
 * checkbox 1 selected Submit form with drop down item 5 selected Submit form
 * with multiple select 1, 2 & 3 Bonus points, submit with a file, and check
 * name on output
 * 
 * allow about 30 mins to an hour for this exercise
 */
public class ManipulationExercise1Test {
	static WebDriver driver;
	static String testURL = "http://compendiumdev.co.uk/selenium/basic_html_form.html";
	static String loadPageTitle = "HTML Form Elements";
	static String submitPageTitle = "Processed Form Details";
	static String commentText = "Here's a comment by Kent";

	@BeforeClass
	public static void startFFDriver() {
		driver = new FirefoxDriver();
	}

	@Before
	public void loadTestURL() {
		driver.get(testURL);
	}

	@AfterClass
	public static void stopFF() {
		driver.quit();
	}

	@Test
	public void submitPageAndVerifyTitle() {
		waitForInitialPageLoad();

		clickSubmitButton();

		waitForSubmitPageTitle();

		assertThat(driver.getTitle(), is(submitPageTitle));
	}

	@Test
	public void clearCommentsSubmitAndCheck() {
		waitForInitialPageLoad();

		WebElement textInput = driver.findElement(By
				.cssSelector("textarea[name='comments']"));
		textInput.click();
		textInput.clear();
		textInput.sendKeys(commentText);

		clickSubmitButton();

		waitForSubmitPageTitle();

		WebElement textOutput = driver.findElement(By
				.cssSelector("#_valuecomments"));

		assertThat(textOutput.getText(), is(commentText));
	}

	@Test
	public void radio2SelectedSubmitAndVerify() {
		waitForInitialPageLoad();

		WebElement radio2 = driver.findElement(By
				.cssSelector("input[type='radio'][value='rd2']"));
		radio2.click();

		clickSubmitButton();

		waitForSubmitPageTitle();

		WebElement radio2Status = driver.findElement(By
				.cssSelector("#_valueradioval"));

		assertThat(radio2Status.getText(), is("rd2"));
	}

	@Test
	public void checkBox1SelectedSubmitAndVerify() {
		// make sure page is fully loaded
		waitForInitialPageLoad();

		// find checkbox1
		WebElement checkBox1 = driver.findElement(By
				.cssSelector("input[type='checkbox'][value='cb1']"));

		// if it's not already selected, click it
		if (!checkBox1.isSelected())
			checkBox1.click();

		// click Submit
		clickSubmitButton();

		// make sure results page is fully loaded
		waitForSubmitPageTitle();

		// find status of radio button selection
		WebElement radio2Status = driver.findElement(By
				.cssSelector("#_valueradioval"));

		// assert rd2 was selected
		assertThat(radio2Status.getText(), is("rd2"));
	}

	@Test
	public void dropDownItem5SelectedSubmitAndVerify() {
		// make sure page is fully loaded
		waitForInitialPageLoad();

		// find dropDownItem5
		WebElement dropDownItem5 = driver.findElement(By
				.cssSelector("select > option[value='dd5']"));
		dropDownItem5.click();
		// assert you've got the right item
		assertTrue("dropDownItem5 is selected", dropDownItem5.isSelected());

		clickSubmitButton();

		// make sure results page is fully loaded
		waitForSubmitPageTitle();

		// find status of desired component
		WebElement dropDownStatus = driver.findElement(By
				.cssSelector("#_valuedropdown"));

		// assert correct selection
		assertThat(dropDownStatus.getText(), is("dd5"));
	}

	@Test
	public void multipleSelectFormSubmitAndVerify() {
		waitForInitialPageLoad();

		// find desired component(s)
		WebElement selectList = driver.findElement(By
				.cssSelector("select[name='multipleselect[]']"));

		// instead of the Rube Goldberg path I was initially following
		// here's a method I found on the web to apply here
		List<WebElement> listOptions = selectList.findElements(By
				.tagName("option"));
		listOptions.get(0).click(); // this first to select 1 and deselect other
		// now 'hold down' CTRL key and select remaining 2
		selectList.sendKeys(Keys.CONTROL);
		listOptions.get(1).click();
		listOptions.get(2).click();

		// now click Submit
		clickSubmitButton();

		// make sure results page is fully loaded
		waitForSubmitPageTitle();

		// find status of desired component
		List<WebElement> multiSelectGroup = driver.findElements(By
				.cssSelector("div#_multipleselect > ul > li"));

		// assert correct selections
		assertThat(multiSelectGroup.get(0).getText(), is("ms1"));
		assertThat(multiSelectGroup.get(1).getText(), is("ms2"));
		assertThat(multiSelectGroup.get(2).getText(), is("ms3"));
		assertThat(multiSelectGroup.get(2).getText(), is(not("ms4")));
	}

	private static void waitForInitialPageLoad() {
		// make sure page is fully loaded
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs(loadPageTitle));
	}

	private static void waitForSubmitPageTitle() {
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.titleIs(submitPageTitle));
	}

	@Test
	public void fileSubmitAndVerify() {
		// make sure page is fully loaded
		waitForInitialPageLoad();

		// find filename Browse button
		WebElement fileInputBox = driver.findElement(By
				.cssSelector("input[type='file']"));
		fileInputBox.sendKeys("C:\\Users\\Jaya\\Desktop\\README.html");

		// now we hope the dialog doesn't come up
		// and we just click submit...
		clickSubmitButton();

		// make sure results page is fully loaded
		waitForSubmitPageTitle();

		// find status of desired component
		WebElement fileUploadStatus = driver.findElement(By
				.cssSelector("#_valuefilename"));

		// assert correct selection
		assertThat(fileUploadStatus.getText(), is("README.html"));

		// WORKS!! :)
	}

	private static void clickSubmitButton() {
		WebElement submit1 = driver.findElement(By
				.cssSelector("input[value='submit']"));
		submit1.click();
	}
}