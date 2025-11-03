package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static utils.DriverSetup.getDriver;

public class CheckOut extends BasePage {
    private final By addToCartBackpack = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By removeBackpack = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    public void clickAddToCart() {
        clickOn(addToCartBackpack);
    }
    public void clickRemove() {
        clickOn(removeBackpack);
    }
    public boolean isRemoveVisible() {
        List<WebElement> elements = getDriver().findElements(removeBackpack);
        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }
    public int getRemoveButtonCount() {
        List<WebElement> elements = getDriver().findElements(removeBackpack);
        return elements.size();
    }
}
