package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.time.Duration;

public class DriverSetup {
    private static String browser_name = System.getProperty("browser", "Chrome");
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
        getDriver().quit();
    }

    public WebDriver getBrowser(String browser_name) {
        if (browser_name.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");
            }
            return new ChromeDriver(options);

        } else if (browser_name.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();

        } else if (browser_name.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else {
            throw new RuntimeException("❌ Browser not supported: " + browser_name);
        }
    }
}
