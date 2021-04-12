package tests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountPage;
import pages.Homepage;
import pages.LoginPage;
import utils.ExtentReports.ExtentTestManager;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests {

    WebDriver driver;
    private String url = "https://demo.opencart.com";
    private String email = "Test89@gmail.com";
    private String password = "password89";
    private String wrongPassword = "wrongpassword";

    static ExtentTest test;
    static ExtentReports report;

    @BeforeAll
    public static void startTest(){
        report = new ExtentReports(System.getProperty("user.dir") + "/ExtentReports/ExtentReportResults.html");
        test = report.startTest("Login Test");
    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        System.out.println("Chrome Browser selected");
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("SuccessFully Login Test 001")
    @Order(1)
    public void ValidUserLoginTest() throws IOException {

        //System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = homepage.clickLoginLink();
        loginPage.setEmailAddress(email);
        loginPage.setPassword("password2");
        AccountPage accountPage = loginPage.clickLoginButton();
        String actualTitle = accountPage.getPageTitle();
        assertNotNull(actualTitle);
        assertEquals("My Account", actualTitle);
        if (driver.getTitle().equals("My Account")){
            test.log(LogStatus.PASS, "Navigated to Account Page");
        }else{
            //test.log(LogStatus.FAIL, "Tested Failed");
            test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + "Test Failed");
        }


        //System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Test
    @DisplayName("Wrong Password Test 002")
    @Order(2)
    public void wrongPasswordTest() {
        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = homepage.clickLoginLink();
        loginPage.setEmailAddress(email);
        loginPage.setPassword(wrongPassword);
        loginPage.clickLoginButton();
        String actualTitle = loginPage.getPageTitle();
        assertNotNull(actualTitle);
        assertEquals("Account Login", actualTitle);
        String alertMessage = loginPage.alertMessage();
        assertNotNull(alertMessage);
        assertEquals("Warning: No match for E-Mail Address and/or Password.", alertMessage);

    }

    @Test
    @DisplayName("Wrong UserName Test 003")
    @Order(3)
    public void wrongUsernameTest() {
        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = homepage.clickLoginLink();
        loginPage.setEmailAddress("asdasdsad@gmail.com");
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        String actualTitle = loginPage.getPageTitle();
        assertNotNull(actualTitle);
        assertEquals("Account Login", actualTitle);
        String alertMessage = loginPage.alertMessage();
        assertNotNull(alertMessage);
        assertEquals("Warning: No match for E-Mail Address and/or Password.", alertMessage);
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }

        report.endTest(test);
        report.flush();
    }

    public static String capture(WebDriver driver) throws IOException {
        String workingDir = System.getProperty("user.dir");
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(workingDir + "/ScreenShots/" + System.currentTimeMillis() + ".png");
        String errorFilePath = destination.getAbsolutePath();
        FileUtils.copyFile(srcFile, destination);
        return errorFilePath;
    }
}
