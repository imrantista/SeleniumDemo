package tests;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Logout;
import utils.DriverSetup;

import static io.qameta.allure.SeverityLevel.CRITICAL;

public class TestLogin extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    Logout logoutPage = new Logout();
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("USER_NAME");
    String password = dotenv.get("PASSWORD");

    @Test
    @Description("This test attempts to valid data")
    @Severity(CRITICAL)
    @Owner("GM Imran")
    @Link(name = "Swag Labs", url = "https://www.saucedemo.com/")
    @Issue("Auth-123")
    @TmsLink("TMS-456")
    public void testWithValidCredential() {
        loginPage.loginAndVerify(username, password);
    }

    @Test
    @Description("This test attempts to invalid data")
    @Severity(CRITICAL)
    @Owner("GM Imran")
    @Link(name = "Swag Labs", url = "https://www.saucedemo.com/")
    @Issue("Auth-123")
    @TmsLink("TMS-456")
    public void testWithInvalidCredential() {
        loginPage.loginWithCredentials(username, "12345");
        loginPage.verifySuccessError(
                By.cssSelector("h3[data-test='error']"),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message mismatch!",
                "Login failed as expected with invalid credentials for " + username
        );
    }

    @Test
    public void testLogoutAfterLogin() {
        loginPage.loginAndVerify(username, password);
        logoutPage.clickOnMenu();
        logoutPage.clickOnLogout();
        Assert.assertEquals(loginPage.getLoginBtnValue(), "Login", "Logout failed");
        System.out.println("Logout successful for user: " + username);
    }
}
