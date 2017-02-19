package fileUpload;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFileUploadExamople {

	public static WebDriver driver;

	@Test
	public static void myFileUploadExample() {
		driver = new FirefoxDriver();
		driver.get("http://sl-test.herokuapp.com/guinea_pig/file_upload");
		WebElement upload = driver.findElement(By.id("myfile"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		upload.sendKeys("F:/Project/integration-tests/soapui-cdi-integration-test/pom.xml");
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.tagName("img"));
		// Assert.assertEquals("darkbulb.jpg (image/jpeg)",
		// driver.findElement(By.tagName("p")).getText());
	}
}
