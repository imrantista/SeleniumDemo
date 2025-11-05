package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static utils.DriverSetup.getDriver;

public class CheckOut extends BasePage {

    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");
    private final By firstNameField = By.cssSelector("[data-test='firstName']");
    private final By lastNameField = By.cssSelector("[data-test='lastName']");
    private final By postalCodeField = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By completeHeader = By.cssSelector("[data-test='complete-header']");
    private final By backToProducts = By.cssSelector("[data-test='back-to-products']");
    private final By subtotalLabel = By.cssSelector(".summary_subtotal_label");

    private By getAddToCartButton(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        return By.cssSelector("[data-test='add-to-cart-" + formattedName + "']");
    }

    private By getRemoveButton(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        return By.cssSelector("[data-test='remove-" + formattedName + "']");
    }

    @Step("Add '{productName}' to cart")
    public void addProductToCart(String productName) {
        clickOn(getAddToCartButton(productName));
    }

    @Step("Verify '{productName}' remove button is visible")
    public boolean isProductRemoveVisible(String productName) {
        return isElementVisible(getRemoveButton(productName));
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(getText(cartBadge));
    }

    @Step("Open cart")
    public void openCart() {
        clickOn(cartBadge);
    }

    @Step("Click checkout")
    public void clickCheckout() {
        clickOn(checkoutButton);
    }

    @Step("Fill checkout form: {firstName} {lastName}, postal code: {postalCode}")
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        typeText(firstNameField, firstName);
        typeText(lastNameField, lastName);
        typeText(postalCodeField, postalCode);
    }

    @Step("Continue checkout")
    public void continueCheckout() {
        clickOn(continueButton);
    }

    @Step("Finish checkout")
    public void finishCheckout() {
        clickOn(finishButton);
    }

    public boolean isCheckoutComplete() {
        return isElementVisible(completeHeader);
    }

    @Step("Go back to products")
    public void goBackToProducts() {
        clickOn(backToProducts);
    }

    @Step("Get price of '{productName}'")
    public double getProductPrice(String productName) {
        WebElement priceElement = getDriver().findElement(
                By.xpath("//div[@data-test='inventory-item-name' and text()='" + productName +
                        "']/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']")
        );
        String priceText = priceElement.getText().replace("$", "");
        return Double.parseDouble(priceText);
    }

    @Step("Get subtotal from checkout page")
    public double getSubtotalFromPage() {
        WebElement subtotalElement = getDriver().findElement(subtotalLabel);
        String subtotalText = subtotalElement.getText().replace("Item total: $", "");
        return Double.parseDouble(subtotalText);
    }
}
