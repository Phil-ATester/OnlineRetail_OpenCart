package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdminLoginPage;
import pages.DashBoardPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardTests {

    WebDriver driver;
    private String url = "https://demo.opencart.com/admin/";

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Chrome Browser selected");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
    }


    @Test
    public void receiveOrdersTest(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        dashBoardPage.getLatestOrders();
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
