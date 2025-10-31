package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static utils.DriverSetup.getDriver;

public class BasePage {


    public WebElement getElement(By locator){
        return getDriver().findElement(locator);
    }

    public void writeOn(By locator, String text){
        getElement(locator).sendKeys(text);
    }

    public void clickOn(By locator){
        getElement(locator).click();
    }

    public void loadPage(String url){
        getDriver().get(url);
    }

}
