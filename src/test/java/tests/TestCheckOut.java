package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOut;
import pages.LoginPage;
import utils.DriverSetup;

public class TestCheckOut extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    CheckOut checkOutPage = new CheckOut();
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("USER_NAME");
    String password = dotenv.get("PASSWORD");

    @Test
    public void testProductCheckOut() {
        loginPage.loginAndVerify(username, password);
        loginPage.handleOptionalBrowserAlert("Change your password", 6);
        checkOutPage.addBackpackToCart();
        Assert.assertTrue(checkOutPage.isBackpackRemoveVisible(), "Backpack Remove button should be visible");
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 1, "Cart badge should be 1 after adding backpack");
        checkOutPage.addBikeLightToCart();
        Assert.assertTrue(checkOutPage.isBikeLightRemoveVisible(), "Bike Light Remove button should be visible");
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 2, "Cart badge should be 2 after adding two items");
        checkOutPage.openCart();
        checkOutPage.clickCheckout();
        checkOutPage.fillCheckoutForm("Imran", "Hossain", "6000");
        checkOutPage.continueCheckout();
        checkOutPage.finishCheckout();
        Assert.assertTrue(checkOutPage.isCheckoutComplete(), "Checkout should complete successfully");
        System.out.println("Checkout completed successfully for user: " + username);
        checkOutPage.goBackToProducts();
        System.out.println("Returned to Products page.");
    }
}
