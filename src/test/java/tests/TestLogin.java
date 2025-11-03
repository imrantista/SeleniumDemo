package tests;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.DriverSetup;

public class TestLogin extends DriverSetup {

    LoginPage loginPage = new LoginPage();
    @Test
    public void testWithValidCredential() {

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("USER_NAME");
        String password = dotenv.get("PASSWORD");
        loginPage.gotToLoginPage();
        loginPage.addUsername(username);
        loginPage.addPassword(password);
        loginPage.clickOnLoginBtn();
        WebElement title = getDriver().findElement(By.className("title"));
        Assert.assertEquals(title.getText(), "Products", "Login failed!");
        System.out.println("Login successful for user: " + username);
    }
    @Test
    public void testWithInvalidCredential() {

        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("USER_NAME");
        loginPage.gotToLoginPage();
        loginPage.addUsername(username);
        loginPage.addPassword("12345");
        loginPage.clickOnLoginBtn();
        WebElement errorMessage = getDriver().findElement(By.cssSelector("h3[data-test='error']"));
        String actualError = errorMessage.getText();
        String expectedError = "Username and password do not match any user in this service";
        Assert.assertEquals(actualError, expectedError, "Error message mismatch!");
        System.out.println("Login failed as expected with invalid credentials for");
    }
}