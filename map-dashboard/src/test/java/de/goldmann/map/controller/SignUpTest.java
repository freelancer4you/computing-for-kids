package de.goldmann.map.controller;

import static org.assertj.core.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.test.utils.TestUtils;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.apps.tests.helpers.VisibilityFunction;
import de.goldmann.map.UiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class SignUpTest extends WebTest {
    private static final Logger LOGGER = LogManager.getLogger(SignUpTest.class);


    @Test
    public void test() {

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {

            driver.get(HOST_ADRESS);

            final WebElement singUpBtn = wait.until(new VisibilityFunction("singUpBtn"));
            singUpBtn.click();

            final WebElement modalSingupDialog = wait.until(new VisibilityFunction("modalSingup"));

            final UserDTO dto = TestUtils.buildUserDto();

            HelperUtils.setInputValue(modalSingupDialog, "firstName", dto.getFirstName());
            HelperUtils.setInputValue(modalSingupDialog, "lastName", dto.getLastName());
            HelperUtils.setInputValue(modalSingupDialog, "userName", dto.getUserName());
            HelperUtils.setInputValue(modalSingupDialog, "email", dto.getEmail());
            HelperUtils.setInputValue(modalSingupDialog, "password", dto.getPassword());

            modalSingupDialog.findElement(By.id("commitSignUpBtn")).click();

            Thread.sleep(1000);

            // TODO logout-btn should be visible
            // logout(wait);
        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }
}
