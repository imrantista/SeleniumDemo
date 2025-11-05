package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOut;
import pages.LoginPage;
import utils.DriverSetup;
import utils.TestDataReader;

public class TestCheckOut extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    CheckOut checkOutPage = new CheckOut();
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("USER_NAME");
    String password = dotenv.get("PASSWORD");

    @Test
    public void testDynamicProductCheckout() {
        String[] products = TestDataReader.getProducts();
        String firstProduct = products[0];
        String secondProduct = products[1];
        loginPage.loginAndVerify(username, password);
        loginPage.handleOptionalBrowserAlert("Change your password", 6);
        checkOutPage.addProductToCart(firstProduct);
        double firstPrice = checkOutPage.getProductPrice(firstProduct);
        Assert.assertTrue(checkOutPage.isProductRemoveVisible(firstProduct));
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 1);
        checkOutPage.addProductToCart(secondProduct);
        double secondPrice = checkOutPage.getProductPrice(secondProduct);
        Assert.assertTrue(checkOutPage.isProductRemoveVisible(secondProduct));
        Assert.assertEquals(checkOutPage.getCartBadgeCount(), 2);
        checkOutPage.openCart();
        checkOutPage.clickCheckout();
        checkOutPage.fillCheckoutForm(
                TestDataReader.getFirstName(),
                TestDataReader.getLastName(),
                TestDataReader.getPostalCode()
        );
        checkOutPage.continueCheckout();
        double pageSubtotal = checkOutPage.getSubtotalFromPage();
        double expectedSubtotal = firstPrice + secondPrice;
        System.out.println("Expected Subtotal: $" + expectedSubtotal);
        System.out.println("Page Subtotal: $" + pageSubtotal);
        Assert.assertEquals(pageSubtotal, expectedSubtotal, 0.01, "Subtotal should match expected total");
        checkOutPage.finishCheckout();
        Assert.assertTrue(checkOutPage.isCheckoutComplete());
        checkOutPage.goBackToProducts();
    }
}
