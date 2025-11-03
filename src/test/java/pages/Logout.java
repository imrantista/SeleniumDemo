package pages;

import org.openqa.selenium.By;

public class Logout extends BasePage {

    private final By menuButton = By.xpath("//button[text()='Open Menu']");
    private final By logoutLink = By.cssSelector("[data-test='logout-sidebar-link']");

    public void clickOnMenu() {
        clickOn(menuButton);
    }

    public void clickOnLogout() {
        clickOn(logoutLink);
    }
}
