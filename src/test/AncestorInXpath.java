package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class AncestorInXpath {

	@Test
	public void testAncestorInXpath() {

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://guru99.com/");

		// Search All elements in 'Popular course' section with the help of
		// ancestor of the anchor whose text is 'SELENIUM'

		List<WebElement> dateBox = driver
				.findElements(By
						.xpath("//div[.//a[text()='SELENIUM']]/ancestor::div[@class='rt-grid-2 rt-omega']/following-sibling::div"));

		// Print all the which are sibling of the element named as 'SELENIUM' in
		// 'Popular course'

		for (WebElement webElement : dateBox) {

			System.out.println(webElement.getText());

		}

		driver.quit();
		
		
		

	}
/*	// Get the content or container    
	WebElement content = driver.findElement(By.id("contentText"));

	//Get the table of users
	WebElement tblUsers = content.findElement(By.xpath(".//table/tbody/tr[2]/td/table/tbody/tr/td[1]/table"));

	// Get the rows which change always as and when users are added
	WebElement allUsers = tblUsers.findElements(By.xpath(".//tbody/tr"));

	// Loop through each row of users table
	for(WebElement user : allUsers) {

	   // Get the username
	   WebElement username = user.findElement(By.xpath(".//td/table/tbody/tr/td[1]/strong[2]"));
	   System.out.println("Username: " + username.getText());*/
	}
}