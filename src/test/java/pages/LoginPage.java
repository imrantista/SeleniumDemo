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
    }

    public void addUsername(String username) {
        typeText(usernameField, username);
    }

    public void addPassword(String password) {
        typeText(passwordField, password);
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
