package pages;

import org.openqa.selenium.By;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage extends BasePage {

    private final Dotenv dotenv = Dotenv.load();
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public void gotToLoginPage() {
        String baseUrl = dotenv.get("BASEURL");
        loadPage(baseUrl);
        System.out.println("Url get successfully");
    }

    public void addUsername(String username) {
        typeText(usernameField, username);
        System.out.println("Username type successfully");
    }

    public void addPassword(String password) {
        typeText(passwordField, password);
        System.out.println("Password type successfully");
    }

    public void clickOnLoginBtn() {
        clickOn(loginBtn);
    }

    public void loginWithCredentials(String username, String password) {
        gotToLoginPage();
        addUsername(username);
        addPassword(password);
        clickOnLoginBtn();
    }

    public void loginAndVerify(String username, String password) {
        loginWithCredentials(username, password);
        verifySuccessError(By.className("title"), "Products", "Login failed!", "Login successful for user: " + username);
    }
    public String getLoginBtnValue() {
        return getElement(loginBtn).getDomAttribute("value");
    }
}