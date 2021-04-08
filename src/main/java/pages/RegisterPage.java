package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegisterPage {

    private WebDriver driver;
    private By firstNameField = By.cssSelector("#input-firstname");
    private By lastNameField = By.cssSelector("#input-lastname");
    private By emailField = By.cssSelector("#input-email");
    private By telephoneField = By.cssSelector("#input-telephone");
    private By passwordField = By.cssSelector("#input-password");
    private By confirmPasswordField = By.xpath("//input[@id='input-confirm']");
    //TODO subscribe webelement
    private By privacyPolicyTickBox = By.cssSelector("input[type=checkbox]:nth-child(2)");
    private By continueButton = By.cssSelector("input.btn.btn-primary");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void setFirstName(String firstName){
        setField(firstNameField, firstName);
    }

    public void setLastName(String lastName){
        setField(lastNameField, lastName);
    }

    public void setEmail(String email){
        setField(emailField, email);
    }

    public void setTelephone(String telephone) {
        setField(telephoneField, telephone);
    }

    public void setPassword(String pw){
        setField(passwordField, pw);
    }

    public void confirmPassword(String password){
        setField(confirmPasswordField, password);
    }

    public void setField(By element, String value){
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void selectSubscribeOption(String option){
        List<WebElement> radioButton = driver.findElements(By.cssSelector(".radio-inline"));
        for (WebElement element: radioButton){
            if(element.getText().contains(option)){
                if(!element.isSelected()){
                    element.click();
//                    System.out.println("Subscribe option selected: " + element.getText());
                    break;
                }
            }
        }
    }

    public void selectAgreedToPrivacyPolicy(){
        if(!driver.findElement(privacyPolicyTickBox).isSelected()){
            driver.findElement(privacyPolicyTickBox).click();
        }
    }

    public AccountPage clickContinueButton(){
        driver.findElement(continueButton).click();
        return new AccountPage(driver);
    }
}
