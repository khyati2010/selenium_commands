package navigation;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MultiWindowHandle {
	WebDriver driver;

	@Before
	public void setup() throws Exception {
		driver = new FirefoxDriver();
		String URL = "https://www.abc.co.in/";
		driver.get(URL);
		driver.manage().window().maximize();
	}

	@Test
	public void test() throws Exception {
		// Opening Calender
		driver.findElement(By.xpath("//img[@alt='Calender']")).click();
		// Storing parent window reference into a String Variable
		String Parent_Window = driver.getWindowHandle();
		// Switching from parent window to child window
		for (String Child_Window : driver.getWindowHandles()) {
			driver.switchTo().window(Child_Window);
			// Performing actions on child window
			driver.findElement(By.id("calendar_month_txt")).click();
			List Months = driver.findElements(By
					.xpath("//div[@id='monthDropDown']//div"));
			int Months_Size = Months.size();
			System.out.println("Month size is:" + Months_Size);
			// Months.get(1).click();
			driver.findElement(
					By.xpath("//*[@id='calendarDiv']/div/table/tbody/tr/td[contains(text(),'16')]"))
					.click();
		}
		// Switching back to Parent Window
		driver.switchTo().window(Parent_Window);
		// Performing some actions on Parent Window
		driver.findElement(By.className("btn_style")).click();
	}

	@After
	public void close() {
		driver.quit();
	}
}
