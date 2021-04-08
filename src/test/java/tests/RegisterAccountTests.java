package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.Homepage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterAccountTests {

    WebDriver driver;
    private String url = "https://demo.opencart.com";
    private String firstName ="John";
    private String lastName = "Test002";
    private String email = "JohnTest002@gmail.com";
    private String telephone = "092343423523";
    private String password = "Tester002";

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Chrome Browser selected");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
    }


    @Test
    public void RegisterAccountTest(){
        Homepage homepage = new Homepage(driver);
        RegisterPage registerPage = homepage.clickRegisterLink();
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setEmail(email);
        registerPage.setTelephone(telephone);
        registerPage.setPassword(password);
        registerPage.confirmPassword(password);
        registerPage.selectSubscribeOption("No");
        registerPage.selectAgreedToPrivacyPolicy();
        AccountPage accountPage = registerPage.clickContinueButton();
        assertEquals(accountPage.getPageTitle(), "Your Account Has Been Created!");
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
