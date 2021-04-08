package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage {

    private WebDriver driver;
    private By userNameField = By.id("input-username");
    private By passwordField = By.id("input-password");
    private By loginBTN = By.cssSelector(".btn");

    public AdminLoginPage (WebDriver driver){
        this.driver = driver;
    }

    public void setUsername(String username){
        driver.findElement(userNameField).clear();
        driver.findElement(userNameField).sendKeys(username);
    }

    public void setPassword(String pw){
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pw);
    }

    public DashBoardPage clickLoginButton(){
        driver.findElement(loginBTN).click();
        return new DashBoardPage(driver);
    }

}

