package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static java.lang.String.format;

public class DatePicker {

    private WebDriver driver;
    private By openCalenderButton = By.cssSelector("#input-date-added ~ span button");
    private By calender = By.cssSelector(".bootstrap-datetimepicker-widget");
    private By period = By.xpath("//th[@class='picker-switch']");
    private By leftArrow = By.cssSelector(".prev");
    private By rightArrow = By.cssSelector(".next");
    private String day_Format = "//td[@class='day' and contains(text(), '%d')]";
    private String input_Format = "//*[@id='input-date-%s']";

    public DatePicker(WebDriver driver){
        this.driver = driver;
    }

    public boolean isCalenderOpen(){
        return driver.findElement(calender).isDisplayed();
    }

    public void open(String tyre){
        String calenderButtonLocator = "#input-date-%s ~ span button";
        By calenderLocator = By.cssSelector(format(calenderButtonLocator, tyre));

        if (!isCalenderOpen()){
            driver.findElement(calenderLocator).click();
        }
    }

    public LocalDate getCurrentDatePeriod(){
        String[] currentPeriod = driver.findElement(period).getText().split(" ");

        String loc = driver.findElement(By.xpath("//*[@class='picker-switch']")).getText();

        return LocalDate.of(
                Integer.parseInt(currentPeriod[1]),
                Month.valueOf(currentPeriod[0].toUpperCase()),
                1);
    }

    public void chooseMonth(LocalDate date) {
        LocalDate currentPeriod = getCurrentDatePeriod();
        long monthsAway = ChronoUnit.MONTHS.between(currentPeriod, date.withDayOfMonth(1));

        By arrow = monthsAway < 0 ? leftArrow : rightArrow;

        for (int i = 0; i < Math.abs(monthsAway); i++){
            driver.findElement(arrow).click();
        }
    }

    public void chooseDay(int dayOfMonth){
        By locator = By.xpath(format(day_Format, dayOfMonth));
        driver.findElement(locator).click();
    }

    public LocalDate getSelectedDate(String type){
        By textLocator = By.xpath(format(input_Format, type));
        String date = driver.findElement(textLocator).getAttribute("value");
        //String date = driver.findElement(By.xpath("//*[@id='input-date-added']")).getAttribute("value");
        String[] currentDate = date.split("-");
        return LocalDate.of(
                Integer.parseInt(currentDate[0]),
                Integer.parseInt(currentDate[1]),
                Integer.parseInt(currentDate[2]));
    }

    public LocalDate chooseDate(LocalDate date, String type) {
        open(type);
        chooseMonth(date);
        chooseDay(date.getDayOfMonth());
        return getSelectedDate(type);
    }
}
