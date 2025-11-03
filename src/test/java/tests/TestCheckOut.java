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
        checkOutPage.addBackpackToCart();
        Assert.assertTrue(checkOutPage.isBackpackRemoveVisible(), "Backpack Remove button should be visible");
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 1, "Cart badge should be 1 after adding backpack");
        checkOutPage.addBikeLightToCart();
        Assert.assertTrue(checkOutPage.isBikeLightRemoveVisible(), "Bike Light Remove button should be visible");
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 2, "Cart badge should be 2 after adding backpack and bike light");
    }
}
