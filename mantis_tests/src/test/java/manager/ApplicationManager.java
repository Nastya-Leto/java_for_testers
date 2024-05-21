package manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private Properties properties;
    private String string;
    private SessionHelper sessionHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Неизвестный браузер %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1296, 696));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }
}

