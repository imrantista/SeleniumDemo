package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import io.qameta.allure.TmsLink;
import pages.LoginPage;
import pages.Logout;
import utils.DriverSetup;

public class TestLogin extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    Logout logoutPage = new Logout();
    Dotenv dotenv = Dotenv.load();
    String username = dotenv.get("USER_NAME");
    String password = dotenv.get("PASSWORD");

    @Test
    @Description("Login with valid credentials should succeed")
    @Severity(CRITICAL)
    @Owner("GM Imran")
    @Link(name = "Swag Labs", url = "https://www.saucedemo.com/")
    @Issue("Auth-123")
    @TmsLink("TMS-456")
    public void doLoginWithValidCredentials() {
        loginPage.loginAndVerify(username, password);
    }

    @Test
    @Description("Login with invalid credentials should fail")
    @Severity(CRITICAL)
    @Owner("GM Imran")
    @Link(name = "Swag Labs", url = "https://www.saucedemo.com/")
    @Issue("Auth-123")
    @TmsLink("TMS-456")
    public void doLoginWithInvalidCredentials() {
        loginPage.InvalidLoginAndVerify(username, "12345");
    }

    @Test
    @Description("Logout should succeed after successful login")
    @Severity(CRITICAL)
    @Owner("GM Imran")
    public void doLogoutAfterLogin() {
        loginPage.loginAndVerify(username, password);
        logoutPage.clickOnMenu();
        logoutPage.clickOnLogout();
        Assert.assertEquals(loginPage.getLoginBtnValue(), "Login", "Logout failed");
        System.out.println("Logout successful for user: " + username);
    }
}
