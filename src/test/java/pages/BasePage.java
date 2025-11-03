package pages;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
        getElement(locator).clear();
        getElement(locator).sendKeys(text);
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
        System.out.println( successMessage);
    }
    public void handleOptionalBrowserAlert(String expectedText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            if (alertText.contains(expectedText)) {
                System.out.println("Alert appeared: " + alertText);
                alert.accept();
                System.out.println("Alert accepted successfully");
            } else {
                System.out.println("Unexpected alert appeared: \"" + alertText + " skipping accept");
            }
        } catch (TimeoutException e) {
            System.out.println("No alert appeared within " + timeoutInSeconds + " seconds continuing without alert.");
        }
    }
}
