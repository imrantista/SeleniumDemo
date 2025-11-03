package pages;

import org.openqa.selenium.By;

public class CheckOut extends BasePage {

    private final By addToCartBackpack = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private final By addToCartBikeLight = By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']");
    private final By removeBackpack = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    private final By removeBikeLight = By.cssSelector("[data-test='remove-sauce-labs-bike-light']");
    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By postalCodeField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By backToProducts = By.cssSelector("[data-test='back-to-products']");

    public void addBackpackToCart() {
        clickOn(addToCartBackpack);
    }

    public void addBikeLightToCart() {
        clickOn(addToCartBikeLight);
    }
    public boolean isBackpackRemoveVisible() {
        return isElementVisible(removeBackpack);
    }

    public boolean isBikeLightRemoveVisible() {
        return isElementVisible(removeBikeLight);
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(getText(cartBadge));
    }

    public void openCart() {
        clickOn(cartBadge);
    }

    public void clickCheckout() {
        clickOn(checkoutButton);
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        typeText(firstNameField, firstName);
        typeText(lastNameField, lastName);
        typeText(postalCodeField, postalCode);
    }

    public void continueCheckout() {
        clickOn(continueButton);
    }

    public void finishCheckout() {
        clickOn(finishButton);
    }

    public boolean isCheckoutComplete() {
        return isElementVisible(completeHeader);
    }

    public void goBackToProducts() {
        clickOn(backToProducts);
    }
}
