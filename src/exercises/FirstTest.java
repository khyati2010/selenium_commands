package exercises;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.Assert.assertTrue;

public class FirstTest {
	public static String OS = System.getProperty("os.name").toLowerCase();

	@Test
	public void driverIsTheKing() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.vericode.com");
		assertTrue(driver.getTitle().contains("ricod"));
		driver.close();
	}

	@Test
	public void correctURL() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.vericode.com");
		System.out.println(driver.getCurrentUrl());
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(
				"http://www.vericode.com/"));
		driver.close();
	}

	@Test
	public void firefoxIsSupportedByWebDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.vericode.com");
		assertTrue(driver.getTitle().contains("ricod"));
		driver.close();
	}

	@Test
	public void ieIsSupportedByWebDriver() {
		if (OS.indexOf("win") >= 0) // run test only on Windows OS
		{
			File file = new File(
					"C:\\Documents and Settings\\tools\\iedriver_32\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			WebDriver driver = new InternetExplorerDriver();
			driver.get("http://www.vericode.com");
			assertTrue(driver.getTitle().contains("ricod"));
			// driver.close();
			driver.quit();
		} else {
			System.out.println("Skipping IE test on Mac OS");
		}
	}

	/*
	 * public void verifyPdf(){ //get current urlpdf file url WebDriver driver =
	 * new HtmlUnitDriver(); URL url = new URL(driver.getCurrentUrl());
	 * 
	 * //create buffer reader object BufferedInputStream fileToParse = new
	 * BufferedInputStream(url.openStream()); PDFParser pdfParser = new
	 * PDFParser("fileToParse.pdf"); pdfParser.parse();
	 * 
	 * //save pdf text into strong variable String pdftxt = new
	 * PDFTextStripper().getText(pdfParser.getPDDocument());
	 * 
	 * //close PDFParser object pdfParser.getPDDocument().close();
	 * Assert.assertTrue(pdftxt.contains(“selenium”)) }
	 */

	public void verifyResponse() throws FailingHttpStatusCodeException,
			MalformedURLException, IOException {
		String url = "http://www.google.com/";
		WebClient webClient = new WebClient();
		HtmlPage htmlPage = webClient.getPage(url);

		// verify response
		Assert.assertEquals(200, htmlPage.getWebResponse().getStatusCode());
		Assert.assertEquals("OK", htmlPage.getWebResponse().getStatusMessage());
		String url1 = "Application Url";
        
		 WebClient webClient1 = new WebClient();
		 DefaultCredentialsProvider credential = new DefaultCredentialsProvider();
		           
		 //Set some example credentials
		 credential.addCredentials("UserName", "Passeord");          
		 webClient1.setCredentialsProvider(credential);
		           
		 HtmlPage  htmlPage1 = webClient1.getPage(url1);

		 //verify response
		 Assert.assertEquals(200, htmlPage1.getWebResponse().getStatusCode());
		 Assert.assertEquals("OK", htmlPage1.getWebResponse().getStatusMessage());
	}
}