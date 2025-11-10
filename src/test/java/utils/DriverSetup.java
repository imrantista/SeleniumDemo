package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {

    private static String browser_name = System.getProperty("browser", "chrome");
    private static boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

    private static final ThreadLocal<WebDriver> LOCAL_DRIVER = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        LOCAL_DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return LOCAL_DRIVER.get();
    }

    @BeforeSuite(alwaysRun = true)
    public void cleanAllureResults() {
        Path allureResults = Paths.get("allure-results");
        if (Files.exists(allureResults)) {
            try {
                Files.walk(allureResults)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                System.out.println("✅ Previous Allure results cleared.");
            } catch (IOException e) {
                System.err.println("⚠️ Failed to clean Allure results: " + e.getMessage());
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void openBrowser() {
        WebDriver browser = getBrowser(browser_name);
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        setDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public WebDriver getBrowser(String browser_name) {
        switch (browser_name.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-password-manager-reauthentication");
                options.addArguments("--disable-blink-features=AutomationControlled");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("autofill.profile_enabled", false);

                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                return new ChromeDriver(options);

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            default:
                throw new RuntimeException("Browser not supported: " + browser_name);
        }
    }
}