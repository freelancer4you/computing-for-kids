package de.goldmann.map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.apps.tests.helpers.VisibilityFunction;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class SignUpTest extends WebTest {
    private static final Logger LOGGER = LogManager.getLogger(SignUpTest.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        userRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        final FluentWait<WebDriver> wait = setupFluentWait(driver);

        driver.get(HOST_ADRESS);

        final WebElement singUpBtn = wait.until(new VisibilityFunction("singUpBtn"));
        singUpBtn.click();

        final WebElement modalSingupDialog = wait.until(new VisibilityFunction("modalSingup"));

        HelperUtils.setInputValue(modalSingupDialog, "firstName", "firstName");
        HelperUtils.setInputValue(modalSingupDialog, "lastName", "lastname");
        HelperUtils.setInputValue(modalSingupDialog, "userName", "userName");
        HelperUtils.setInputValue(modalSingupDialog, "email", "test@gmx.de");
        HelperUtils.setInputValue(modalSingupDialog, "password", "Testgmxde1");

        modalSingupDialog.findElement(By.id("commitSignUpBtn")).click();

        Thread.sleep(1000);

        logout(wait);

    }
}
