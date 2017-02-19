package examples;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ImageComparison {

	public WebDriver driver;
	private String baseUrl;

	@BeforeSuite
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.google.co.in/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testImageComparison() throws IOException, InterruptedException {
		driver.navigate().to(baseUrl);
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		Thread.sleep(3000);
		FileUtils.copyFile(screenshot, new File("GoogleOutput.jpg"));

		File fileInput = new File("GoogleInput.jpg");
		File fileOutPut = new File("GoogleOutput.jpg");

		BufferedImage bufileInput = ImageIO.read(fileInput);
		DataBuffer dafileInput = bufileInput.getData().getDataBuffer();
		int sizefileInput = dafileInput.getSize();
		BufferedImage bufileOutPut = ImageIO.read(fileOutPut);
		DataBuffer dafileOutPut = bufileOutPut.getData().getDataBuffer();
		int sizefileOutPut = dafileOutPut.getSize();
		Boolean matchFlag = true;
		if (sizefileInput == sizefileOutPut) {
			for (int j = 0; j < sizefileInput; j++) {
				if (dafileInput.getElem(j) != dafileOutPut.getElem(j)) {
					matchFlag = false;
					break;
				}
			}
		} else
			matchFlag = false;
		Assert.assertTrue(matchFlag, "Images are not same");
	}
}
