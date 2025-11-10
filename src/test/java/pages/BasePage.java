package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static utils.DriverSetup.getDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public WebElement getElement(By locator) {
        return getDriver().findElement(locator);
    }

    public void clickOn(By locator) {
        getElement(locator).click();
    }

    public void typeText(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementVisible(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    public String getText(By locator) {
        return getElement(locator).getText();
    }

    public void loadPage(String url) {
        getDriver().get(url);
    }

    public void verifySuccessError(By locator, String expectedText, String failMessage, String successMessage) {
        WebElement element = getElement(locator);
        Assert.assertEquals(element.getText(), expectedText, failMessage);
        System.out.println(successMessage);
    }
}