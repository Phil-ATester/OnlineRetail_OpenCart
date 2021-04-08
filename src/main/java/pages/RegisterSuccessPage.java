package pages;

import org.openqa.selenium.WebDriver;

public class RegisterSuccessPage {

    private WebDriver driver;

    public RegisterSuccessPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyPage(String title){
        return driver.getTitle().contains(title);
    }
}
