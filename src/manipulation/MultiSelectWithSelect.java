package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
* A redo of the earlier multi select exercise
* using a new Selenium 'Select' class
*/
public class MultiSelectWithSelect {
    static WebDriver driver;

    @BeforeClass
    public static void startFF() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void stopFF() {
        driver.quit();
    }

    @Test
    public void testSelectClassMultiSelect() {
        // load page
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");
        // wait for initial page load
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("HTML Form Elements"));

        // use Select class to select
        Select multiSelect = new Select(driver.findElement(
                By.cssSelector("select[name=\"multipleselect[]\"]")));

        multiSelect.deselectAll();
        // verify no options selected
        assertThat(multiSelect.getAllSelectedOptions().size(), is(0));

        // now select the ones we want using different options
        multiSelect.selectByVisibleText("Selection Item 1");
        multiSelect.selectByIndex(1);
        multiSelect.selectByValue("ms3");

        // now submit page and wait for page load
        WebElement submitButton = driver.findElement(
                By.cssSelector("input[type='submit']"));
        submitButton.click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleIs("Processed Form Details"));

        // assert correct selections
        List<WebElement> bananas = driver.findElements(
                By.cssSelector("div[id='_multipleselect'] ul li"));

        assertThat(bananas.get(0).getText(), is("ms1"));
        assertThat(bananas.get(1).getText(), is("ms2"));
        assertThat(bananas.get(2).getText(), is("ms3"));
        assertThat(bananas.get(2).getText(), is(not("ms4")));
    }
}