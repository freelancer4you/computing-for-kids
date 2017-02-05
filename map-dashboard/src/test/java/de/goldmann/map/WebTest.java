package de.goldmann.map;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.apps.tests.helpers.VisibilityFunction;
import de.goldmann.apps.tests.helpers.WaitDefinition;

public abstract class WebTest {

    protected static final String      HOST_ADRESS = "http://localhost:8080";
    private static final String SELECT_COURSES_LINK = "#navbar > ul:nth-child(1) > li.dropdown > a";
    private static final String        SELCTOR_KIDS_COURSE_LINK = "#navbar > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(1) > a";

    protected WebDriver driver;
    @Autowired
    protected DefaultAccountRepository userRepository;

    @Before
    public void setUp() throws Exception {
        driver = setupDriver();
        userRepository.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private String getChromeDriverPath() {
        final Path currentRelativePath = Paths.get("");
        final Path parent = currentRelativePath.toAbsolutePath().getParent();

        final String driverFile = parent.toString() + FileSystems.getDefault().getSeparator() + "chromedriver";
        return SystemUtils.IS_OS_WINDOWS == false ? driverFile : driverFile + ".exe";
    }

    protected WebDriver setupDriver() {
        final DesiredCapabilities dc = new DesiredCapabilities();
        final LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.FINE);
        dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        final ChromeDriver chromeDriver = new ChromeDriver(dc);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    protected FluentWait<WebDriver> setupFluentWait(final WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
        final FluentWait<WebDriver> wait = new WaitDefinition(driver).getDefinition();
        return wait;
    }

    protected void logout(final FluentWait<WebDriver> wait) {
        final WebElement logoutBtn = wait.until(new VisibilityFunction(By.id("logoutBtn")));
        logoutBtn.click();
    }

    protected void login(final FluentWait<WebDriver> wait, final String userName, final String password) {
        final WebElement singUpBtn = wait.until(new VisibilityFunction(By.id("loginBtn")));
        singUpBtn.click();

        final WebElement loginModal = wait.until(new VisibilityFunction(By.id("modalLogin")));

        HelperUtils.setInputValue(loginModal, "username", userName);
        HelperUtils.setInputValue(loginModal, "password", password);

        loginModal.findElement(By.id("submitLoginBtn")).click();
    }

    protected void kursSeiteOeffnen(final FluentWait<WebDriver> wait) throws InterruptedException {
        Thread.sleep(2000);
        final WebElement courseLink = wait.until(new VisibilityFunction(By.cssSelector(SELECT_COURSES_LINK)));
        courseLink.click();

        final WebElement kidsCourseLink = wait.until(new VisibilityFunction(By.cssSelector(SELCTOR_KIDS_COURSE_LINK)));
        kidsCourseLink.click();
    }

}
