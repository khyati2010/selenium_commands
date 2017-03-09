package fileUpload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadFile {

	static WebDriver driver;
	String URL = "application URL";

	@Test
	public void testUpload() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get(URL);
		WebElement element = driver.findElement(By.name("uploadsubmit"));
		// To input the filename along with path
		element.sendKeys("D:/file.txt");
		// To click on the submit button (Not the browse button)
		driver.findElement(By.name("SubmitBtn")).click();
		String checkText = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("File uploaded successfully", checkText);
	}
}