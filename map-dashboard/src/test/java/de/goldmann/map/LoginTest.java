package de.goldmann.map;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.apps.tests.helpers.VisibilityFunction;
import de.goldmann.apps.tests.helpers.WaitDefinition;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class LoginTest
{

    @Autowired
    private UserRepository userRepository;

    private NewUserDTO     dto;

    @Before
    public void setUp() throws Exception
    {
        userRepository.deleteAll();
        dto = new NewUserDTO("firstName", "lastName", "userName", "test@gmx.de", "Password3", true);
        User user = new User(dto);
        userRepository.save(user);
    }

    @Test
    public void testLogin() throws Exception
    {
        final DesiredCapabilities dc = new DesiredCapabilities();
        final LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.FINE);
        dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        final WebDriver driver = new FirefoxDriver(dc);// new HtmlUnitDriver();

        try
        {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
            final FluentWait<WebDriver> wait = new WaitDefinition(driver).getDefinition();

            driver.get("http://localhost:8080");

            final WebElement singUpBtn = wait.until(new VisibilityFunction("loginBtn"));
            singUpBtn.click();

            final WebElement loginModal = wait.until(new VisibilityFunction("modalLogin"));

            HelperUtils.setInputValue(loginModal, "username", dto.getUserName());
            // HelperUtils.setInputValue(loginModal, "email", "test@gmx.de");
            HelperUtils.setInputValue(loginModal, "password", dto.getPassword());

            loginModal.findElement(By.id("submitLoginBtn")).click();

            Thread.sleep(100);

            assertNotNull(driver.findElement(By.id("totalClicks")));
            assertNotNull(driver.findElement(By.id("totalImpressions")));
            assertNotNull(driver.findElement(By.id("totalCtr")));
            assertNotNull(driver.findElement(By.id("totalCpm")));
            assertNotNull(driver.findElement(By.id("fromDate")));

            final WebElement logoutBtn = wait.until(new VisibilityFunction("logoutBtn"));
            logoutBtn.click();

            // analyzeLog(driver);

        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
        finally
        {
            driver.quit();
        }
    }

}
