package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By continueButton = By.xpath("//a[contains(text(),'Continue')]");
    private By emailAddressField = By.xpath("//input[@id='input-email']");
    private By passwordField = By.xpath("//input[@id='input-password']");
    private By loginButton = By.xpath("//input[@type='submit' and @value='Login']");
    private By alert = By.cssSelector(".alert");
    private By forgottenPasswordLink = By.xpath("//a[contains(text(),'Forgotten Password')]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmailAddress(String email){
        driver.findElement(emailAddressField).sendKeys(email);
    }

    public void setPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public AccountPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new AccountPage(driver);
    }

    public String alertMessage(){
        return driver.findElement(alert).getText();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
