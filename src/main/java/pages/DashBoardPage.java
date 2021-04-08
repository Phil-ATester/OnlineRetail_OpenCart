package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashBoardPage {

    private WebDriver driver;

    private By orderTable = By.cssSelector(".table>tbody>tr");

    public DashBoardPage(WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void getLatestOrders(){
        List<WebElement> orderList = driver.findElements(orderTable);

        for (WebElement order : orderList){
            System.out.println(order.getText());
        }
    }

    public OrderPage selectOrders(){
        //driver.findElement(By.xpath("//a[contains(@href,'sale/order')]")).click();
        //ul[@id='collapse26']//a
        driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[1]/div/div[3]/a")).click();
        return new OrderPage(driver);
    }

}
