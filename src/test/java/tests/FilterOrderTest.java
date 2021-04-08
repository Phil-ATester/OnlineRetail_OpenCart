package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdminLoginPage;
import pages.DashBoardPage;
import pages.OrderPage;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterOrderTest {

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
    @DisplayName("Filter by Order Id Test")
    public void FilterOrderIDTest(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        //adminLoginPage.setUsername("demo");
        //adminLoginPage.setPassword("demo");
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        System.out.println(orderPage.getHeading());
        orderPage.enterOrderID("11359");
        assertEquals(orderPage.getOrderList(), 1);
    }

    @Test
    @DisplayName("Filter By Customer Name Test")
    public void FilterCustomerNameTest() {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        //adminLoginPage.setUsername("demo");
        //adminLoginPage.setPassword("demo");
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        System.out.println(orderPage.getHeading());
        orderPage.enterCustomer("john v");
        assertEquals(orderPage.getOrderList(), 10);
    }

    @Test
    @DisplayName("Filter By Order Status")
    public void FilterByOrderStatus(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        //adminLoginPage.setUsername("demo");
        //adminLoginPage.setPassword("demo");
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        System.out.println(orderPage.getHeading());
        orderPage.selectByStatus("Complete");
        assertEquals(orderPage.getOrderList(), 1);
    }

    @Test
    @DisplayName("Filter By Added Date")
    public void FilterByDateAddedTest(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        //adminLoginPage.setUsername("demo");
        //adminLoginPage.setPassword("demo");
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        assertEquals(orderPage.getHeading(), "Orders");
        LocalDate dateToSelect = LocalDate.of(2021, Month.APRIL, 7);
        LocalDate selectedDate = orderPage.selectDate(dateToSelect, "added");
        assertEquals(dateToSelect, selectedDate);
        orderPage.selectFilterButton();
        assertEquals(orderPage.getOrderList(), 5);
    }

    @Test
    @DisplayName("Filter By Added Date")
    public void FilterByDateModifiedTest(){
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        //adminLoginPage.setUsername("demo");
        //adminLoginPage.setPassword("demo");
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        assertEquals(orderPage.getHeading(), "Orders");
        orderPage.selectDateModified("2021-04-06");
//        LocalDate dateToSelect = LocalDate.of(2021, Month.APRIL, 6);
//        LocalDate selectedDate = orderPage.selectDate(dateToSelect, "modified");
//        assertEquals(dateToSelect, selectedDate);
        assertEquals(orderPage.getOrderList(), 5);
    }

    @Test
    @DisplayName("Test data class")
    public void DateClassTest() {
        AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
        DashBoardPage dashBoardPage = adminLoginPage.clickLoginButton();
        assertEquals(dashBoardPage.getPageTitle(), "Dashboard");
        OrderPage orderPage = dashBoardPage.selectOrders();
        System.out.println(orderPage.getHeading());
        LocalDate dateToSelect = LocalDate.of(2021, Month.MARCH, 6);
        LocalDate selectedDate = orderPage.selectDate(dateToSelect, "added");
        assertEquals(dateToSelect, selectedDate);
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
