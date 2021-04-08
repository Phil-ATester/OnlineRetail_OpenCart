package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Homepage {

    private WebDriver driver;
    private By myAccount = By.cssSelector("#top-links > ul > li.dropdown > a");
    private By login = By.xpath("//*[@id='top-links']//a[contains(@href, 'login')]");
    private By searchField = By.cssSelector("#search>input");
    private By searchButton = By.cssSelector("#search>span>button");
    //*[@id="search"]/span/button
    
    public Homepage (WebDriver driver){
        this.driver = driver;
    }

    public LoginPage clickLoginLink() {
        driver.findElement(By.xpath("//*[@id='top-links']/ul/li[2]/a/span[1]")).click();

        List<WebElement> list = driver.findElements(By.xpath("//*[@id='top-links']/ul/li[2]/ul/li/a"));

        for (WebElement ele : list){
            if (ele.getAttribute("innerHTML").contains("Login")){
                ele.click();
                break;
            }
        }

        return new LoginPage(driver);
    }

    public RegisterPage clickRegisterLink() {
        driver.findElement(By.xpath("//*[@id='top-links']/ul/li[2]/a/span[1]")).click();

        List<WebElement> list = driver.findElements(By.xpath("//*[@id='top-links']/ul/li[2]/ul/li/a"));

        for (WebElement ele : list){
            if (ele.getAttribute("innerHTML").contains("Register")){
                ele.click();
                break;
            }
        }

        return new RegisterPage(driver);
    }

    public SearchPage search(String item){
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(item);
        driver.findElement(searchButton).click();
        return new SearchPage(driver);
    }
}


