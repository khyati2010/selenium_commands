package test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyBoardActions {

	public void KeyPress() {
		WebDriver driver = new FirefoxDriver();
		Actions keyPress = new Actions(driver);
		keyPress.keyDown(Keys.ARROW_LEFT).perform();
		keyPress.keyUp(Keys.BACK_SPACE).perform();
		
	}

}
