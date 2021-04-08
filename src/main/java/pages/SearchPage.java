package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {

    private WebDriver driver;

    public SearchPage (WebDriver driver){
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public int getSearchResult(){
        List<WebElement> searchResult = driver.findElements(By.cssSelector(".product-layout"));
        return searchResult.size();
    }
}

