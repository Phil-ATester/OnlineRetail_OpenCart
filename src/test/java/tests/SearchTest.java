package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Homepage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {

    WebDriver driver;
    private String url = "https://demo.opencart.com";

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Chrome Browser selected");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
    }


    @Test
    public void searchTest(){
        Homepage homepage = new Homepage(driver);
        SearchPage searchPage = homepage.search("iMac");
        System.out.println("Result: " + searchPage.getPageTitle());
        assertEquals(searchPage.getPageTitle(), "Search - iMac");
        assertEquals(searchPage.getSearchResult(), 1);
    }

    //*[@id="content"]/div[3]

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
