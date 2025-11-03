package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static utils.DriverSetup.getDriver;
import org.testng.Assert;

public class BasePage {


    public WebElement getElement(By locator){
        return getDriver().findElement(locator);
    }

    public String getAttributeValue(By locator, String attribute){
        return  getElement(locator).getDomAttribute(attribute);
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
    public void verifySuccessError(By locator, String expectedText, String failMessage, String successMessage) {
        WebElement element = getElement(locator);
        Assert.assertEquals(element.getText(), expectedText, failMessage);
        System.out.println(successMessage);
    }

}
