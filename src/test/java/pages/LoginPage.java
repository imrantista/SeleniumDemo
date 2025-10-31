package pages;

import org.openqa.selenium.By;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginPage extends BasePage{
    private final Dotenv dotenv = Dotenv.load();
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginBtn = By.id("login-button");

    public void gotToLoginPage(){
        Dotenv dotenv = Dotenv.load();
        String baseUrl = dotenv.get("BASEURL");
        loadPage(baseUrl);
    }
    public void addUsername(String username){
            writeOn(usernameField, username);
    }
    public void addPassword(String password){
        writeOn(passwordField, password);
    }

    public void clickOnLoginBtn(){
        clickOn(loginBtn);
    }
}
