import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseGridClass {
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected WebDriver driver;
    private DesiredCapabilities capabilities;
    private URL remoteSeleniumGrid;

    @Before
    public void startBrowser() throws MalformedURLException {
        if (threadLocalDriver.get() != null) {
            driver = threadLocalDriver.get();
            return;
        }

        capabilities = DesiredCapabilities.chrome();
//        capabilities = DesiredCapabilities.firefox();
        remoteSeleniumGrid = new URL("http://192.168.55.100:4444/wd/hub");
        driver = new RemoteWebDriver(remoteSeleniumGrid, capabilities);
        threadLocalDriver.set(driver);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            driver.quit();
            driver = null;
        }));
    }

    @After
    public void finishBrowser() {
//        driver.quit();
    }
}