package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderPage {

    private WebDriver driver;
    private By orderIdTextBox = By.cssSelector("#input-order-id");
    private By customerTextBox = By.cssSelector("#input-customer");
    private By orderStatusDropDown = By.cssSelector("#input-order-status");
    private By totalTextBox = By.cssSelector("#inputTotal");

    private By filterButton = By.xpath("//*[@id='button-filter']");
    private By statusField = By.cssSelector("#input-order-status");
    private By dateAddedField = By.cssSelector("#input-date-added");
    private By dateModifiedField = By.id("input-date-modified");

    private By dataAddIcon = By.xpath("//input[@id='input-date-added']//following::span//button");


    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeading(){
        return driver.findElement(By.xpath("//div[@id='content']//h1")).getText();
    }

    public void enterOrderID(String id){
        driver.findElement(orderIdTextBox).clear();
        driver.findElement(orderIdTextBox).sendKeys(id);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.findElement(filterButton).click();
    }

    public void enterCustomer(String name){
        driver.findElement(customerTextBox).clear();
        driver.findElement(customerTextBox).sendKeys(name);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.findElement(filterButton).click();
    }

    public void selectByStatus(String status){
        Select statusList = new Select(driver.findElement(statusField));
        statusList.selectByVisibleText(status);
        driver.findElement(filterButton).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    public void selectDateAdded(String date){
        driver.findElement(dateAddedField).click();
        driver.findElement(dateAddedField).sendKeys(date);
        driver.findElement(filterButton).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    public void selectDateModified(String modifiedDate){
        driver.findElement(dateModifiedField).click();
        driver.findElement(dateModifiedField).sendKeys(modifiedDate);
        driver.findElement(filterButton).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    public LocalDate selectDate(LocalDate date, String type){
       DatePicker datePicker = new DatePicker(driver);
       return datePicker.chooseDate(date, type);
    }

    public void selectFilterButton(){
        driver.findElement(filterButton).click();
    }

//    public void selectDate(String year, String month, String day){
//
//    }
//
//    public void selectDay(String day){
//        List<WebElement> column = driver.findElements(By.xpath(""));
//
//        for(WebElement cell:column){
//            if(cell.getText().equalsIgnoreCase(day)){
//                cell.click();
//                break;
//            }
//        }
//    }
//
//    public void selectMonth(String month){
//        String currentMonth = driver.findElement(By.cssSelector(".datepicker-days > table > thead > tr:nth-child(1) > th.picker-switch")).getText();
//
//
//    }

    public int getOrderList(){
        List<WebElement> orderList = driver.findElements(By.cssSelector(".table>tbody>tr"));
        return orderList.size();
    }
}
