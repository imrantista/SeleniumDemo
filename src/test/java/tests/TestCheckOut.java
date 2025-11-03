package tests;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOut;
import pages.LoginPage;
import pages.Logout;
import utils.DriverSetup;

public class TestCheckOut extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    Logout logoutPage = new Logout();
    CheckOut checkOutPage = new CheckOut();
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("USER_NAME");
    String password = dotenv.get("PASSWORD");

    @Test
    public void testProductCheckOut() {
        // Step 1: Login
        loginPage.loginWithCredentials(username, password);

        // Verify login success
        loginPage.verifySuccessError(
                By.className("title"),
                "Products",
                "Login failed!",
                "Login successful for user: " + username
        );
        checkOutPage.clickAddToCart();
        Assert.assertTrue(checkOutPage.isRemoveVisible(), "Remove button should be visible after adding to cart");
        int count = checkOutPage.getRemoveButtonCount();
        System.out.println("Remove button count after first click: " + count);
        Assert.assertEquals(count, 1, "Remove button count should be 1 after first click");
        checkOutPage.clickAddToCart();

        // Step 6: Verify Remove button count increases or remains same
        int newCount = checkOutPage.getRemoveButtonCount();
        System.out.println("Remove button count after second click: " + newCount);
        Assert.assertTrue(newCount >= count, "Remove button count should increase or remain same after second click");
    }
}
