package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckOut extends BasePage {

    private final By addToCartBackpack = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By addToCartBikeLight = By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']");
    private final By removeBackpack = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    private final By removeBikeLight = By.cssSelector("[data-test='remove-sauce-labs-bike-light']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    public void addBackpackToCart() {
        clickOn(addToCartBackpack);
    }
    public void addBikeLightToCart() {
        clickOn(addToCartBikeLight);
    }
    public boolean isBackpackRemoveVisible() {
        WebElement element = getElement(removeBackpack);
        return element.isDisplayed();
    }
    public boolean isBikeLightRemoveVisible() {
        WebElement element = getElement(removeBikeLight);
        return element.isDisplayed();
    }
    public int getCartBadgeCount() {
        WebElement badge = getElement(cartBadge);
        return Integer.parseInt(badge.getText());
    }
}
