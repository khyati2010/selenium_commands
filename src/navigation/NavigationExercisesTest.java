package navigation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertTrue;

public class NavigationExercisesTest {

    static WebDriver driver;

    final static String PROTOCOL = "http";
    final static String DOMAIN = "compendiumdev.co.uk";
    final static String BASE_URL = PROTOCOL + "://" + DOMAIN;

    public static String URL_1;
    public static String URL_2;
    public static String URL_3;
    public static String URL_4;
    public static String URL_5;

    // Openers and Closers

    @BeforeClass
    public static void openFireFoxBrowserAndInitializeURLs()
                throws MalformedURLException {
        driver = new FirefoxDriver();

        // initialize URL's

        URL_1 = new URL(PROTOCOL, DOMAIN, "/selenium").toString();
        URL_2 = new URL(PROTOCOL, DOMAIN, "/selenium/search.php").toString();
        URL_3 = new URL(PROTOCOL, DOMAIN, "/selenium/basic_html_form.html").toString();
        URL_4 = new URL(PROTOCOL, DOMAIN, "/selenium/basic_web_page.html").toString();
        URL_5 = new URL(PROTOCOL, DOMAIN, "/selenium/refresh.php").toString();
    }

    @AfterClass
    public static void closeFireFoxBrowser() {
        driver.close();
    }

    // Some preliminaries

    @Test
    public void navigateWithGet() {
        driver.get(BASE_URL + "/selenium");
    }

    @Test
    public void getWithURL()
            throws MalformedURLException {
        URL webPage = new URL(PROTOCOL, DOMAIN, "/selenium");
        driver.get(webPage.toString());
        assertTrue("Navigate to selenium", driver.getTitle().contains("Selenium Simplified"));
    }

    // Test URL 1

    @Test
    public void navigateToSeleniumAndCheckTitle() {
        driver.get(URL_1);
        assertTrue("Navigate to selenium", driver.getTitle().contains("Selenium Simplified"));
    }

    @Test
    public void navigateForwardSeleniumAndCheckTitle() {
        driver.get(URL_1);
        driver.get(BASE_URL + "/selenium");
        driver.navigate().back();
        driver.navigate().forward();
        assertTrue("Navigate forward to selenium", driver.getTitle().contains("Selenium Simplified"));
    }

    @Test
    public void navigateBackSeleniumAndCheckTitle() {
        driver.get(URL_1);
        driver.get("http://www.google.com");
        driver.navigate().back();
        assertTrue("Navigate back to selenium", driver.getTitle().contains("Selenium Simplified"));
    }

    @Test
    public void navigateRefreshSeleniumAndCheckTitle() {
        driver.get(URL_1);
        driver.navigate().refresh();
        assertTrue("Refreshed to selenium", driver.getTitle().contains("Selenium Simplified"));
    }

    // Test URL 2

    @Test
    public void navigateToSeleniumSearchAndCheckTitle() {
        driver.get(URL_2);
        assertTrue("Navigate to searchPHP", driver.getTitle().contains("Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateForwardSeleniumSearchAndCheckTitle() {
        driver.get("http://www.google.com");
        driver.get(URL_2);
        driver.navigate().back();
        driver.navigate().forward();
        assertTrue("Navigate forward to searchPHP", driver.getTitle().contains("Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateBackSeleniumSearchAndCheckTitle() {
        driver.get(URL_2);
        driver.get("http://www.google.com");
        driver.navigate().back();
        assertTrue("Navigate back to searchPHP", driver.getTitle().contains("Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateRefreshSeleniumSearchAndCheckTitle() {
        driver.get(URL_2);
        driver.navigate().refresh();
        assertTrue("Refresh to searchPHP", driver.getTitle().contains("Selenium Simplified Search Engine"));
    }

    // Test URL 3

    @Test
    public void navigateToSeleniumBasicHTMLAndCheckTitle() {
        driver.get(URL_3);
        assertTrue("Navigate to HTML page", driver.getTitle().contains("HTML Form Elements"));
    }

     @Test
     public void navigateForwardSeleniumBasicHTMLAndCheckTitle() {
         driver.get("http://www.google.com");
         driver.get(URL_3);
         driver.navigate().back();
         driver.navigate().forward();
         assertTrue("Navigate forward to HTML page", driver.getTitle().contains("HTML Form Elements"));
     }

     @Test
     public void navigateBackSeleniumBasicHTMLAndCheckTitle() {
         driver.get(URL_3);
         driver.get("http://www.google.com");
         driver.navigate().back();
         assertTrue("Navigate back to HTML page", driver.getTitle().contains("HTML Form Elements"));
     }

     @Test
     public void navigateRefreshSeleniumBasicHTMLAndCheckTitle() {
         driver.get(URL_3);
         driver.navigate().refresh();
         assertTrue("Refresh to HTML page", driver.getTitle().contains("HTML Form Elements"));
     }

    // Test URL 4

    @Test
    public void navigateToSeleniumBasicWebAndCheckTitle() {
        driver.get(URL_4);
        assertTrue("Navigate to Basic page", driver.getTitle().contains("Basic Web Page Title"));
    }

    @Test
    public void navigateForwardSeleniumBasicWebAndCheckTitle() {
        driver.get("http://www.google.com");
        driver.get(URL_4);
        driver.navigate().back();
        driver.navigate().forward();
        assertTrue("Navigate forward to Basic page", driver.getTitle().contains("Basic Web Page Title"));
    }

    @Test
    public void navigateBackSeleniumBasicWebAndCheckTitle() {
        driver.get(URL_4);
        driver.get("http://www.google.com");
        driver.navigate().back();
        assertTrue("Navigate back to Basic page", driver.getTitle().contains("Basic Web Page Title"));
    }

    @Test
    public void navigateRefreshSeleniumBasicWebAndCheckTitle() {
        driver.get(URL_4);
        driver.navigate().refresh();
        assertTrue("Refresh to Basic page", driver.getTitle().contains("Basic Web Page Title"));
    }

    // Test URL 5
    // Tricky, with changing title

    @Test
    public void navigateToSeleniumRefreshAndCheckTitle() {
        driver.get(URL_5);
        assertTrue("Navigate to refreshPHP", driver.getTitle().contains("Refreshed Page on"));
    }

    @Test
    public void navigateForwardSeleniumRefreshAndCheckTitle() {
        driver.get("http://www.google.com");
        driver.get(URL_5);
        driver.navigate().back();
        driver.navigate().forward();
        assertTrue("Navigate forward to refreshPHP", driver.getTitle().contains("Refreshed Page on"));
    }

    @Test
    public void navigateBackSeleniumRefreshAndCheckTitle() {
        driver.get(URL_5);
        driver.get("http://www.google.com");
        driver.navigate().back();
        assertTrue("Navigate back to refreshPHP", driver.getTitle().contains("Refreshed Page on"));
    }


    @Test
    public void navigateRefreshSeleniumRefreshAndCheckTitle() {
        driver.get(URL_5);
        driver.navigate().refresh();
        String title = driver.getTitle();
        assertTrue("Show refreshPHP on refresh", title.matches("Refreshed Page on [0-9]{10}"));

        /** Now let's go deeper with what we learned from the explanation.
This part is pretty much (but not totally) verbatim from his as much
of this is new to me. But hey, at least I'm typing it in myself! */

        final String refreshTitleConstant = "Refreshed Page on ";
        assertTrue(driver.getTitle().startsWith(refreshTitleConstant));

        Long startTime = Long.parseLong(
            driver.getTitle().replaceFirst(refreshTitleConstant, ""));

        // Now sleep long enough (2 secs) to get a new timestamp from the title
        // on refresh.
        try{ Thread.sleep(2000); }
        catch(InterruptedException e){ /** ignore interrupt */ }

        driver.navigate().refresh();

        assertTrue(driver.getTitle().startsWith(refreshTitleConstant));

        Long endTime = Long.parseLong(
                driver.getTitle().replaceFirst(refreshTitleConstant, ""));

        assertTrue("Expected " + endTime + " > " + startTime, (endTime > startTime));
    }
}