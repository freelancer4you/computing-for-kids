package de.goldmann.map;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import de.goldmann.apps.tests.helpers.WaitDefinition;

public class WebTest {

    protected static final String HOST_ADRESS = "http://localhost:8080";

    protected WebDriver setupDriver() {
        final DesiredCapabilities dc = new DesiredCapabilities();
        final LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.FINE);
        dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        // TODO this needs to be dynamic
        String pathChromeDriver = "/home/goldmann/development/workspace-sts/computing-for-kids/chromedriver";
        final String windowsExt = ".exe";

        if(SystemUtils.IS_OS_WINDOWS){
            pathChromeDriver += windowsExt;
        }

        System.setProperty("webdriver.chrome.driver", pathChromeDriver);

        final WebDriver driver = new ChromeDriver(dc); //new FirefoxDriver(dc);
        return driver;
    }

    protected FluentWait<WebDriver> setupFluentWait(final WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
        final FluentWait<WebDriver> wait = new WaitDefinition(driver).getDefinition();
        return wait;
    }

}
