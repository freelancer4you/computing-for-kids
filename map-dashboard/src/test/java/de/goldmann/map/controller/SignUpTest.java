package de.goldmann.map.controller;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.test.utils.TestUtils;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.apps.tests.helpers.VisibilityFunction;
import de.goldmann.map.UiApplication;
import de.goldmann.map.WebTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class SignUpTest extends WebTest {
    private static final Logger LOGGER = LogManager.getLogger(SignUpTest.class);

    private static final String SELCTOR_REISTER_BTN = "#container > div.ng-scope > div > div:nth-child(5) > div > div > div.clearfixHeader > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(3) > a";

    @Test
    @Sql("testSignUp.sql")
    public void test() {

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {

            driver.get(HOST_ADRESS + "#/courses/kids");

            Thread.sleep(2000);
            final WebElement registerBtn = wait.until(new VisibilityFunction(By.cssSelector(SELCTOR_REISTER_BTN)));
            registerBtn.click();

            final UserDTO dto = TestUtils.buildUserDto();

            final WebElement signUpForm = wait.until(new VisibilityFunction(By.id("signUpForm")));

            HelperUtils.setInputValue(signUpForm, "firstName", dto.getFirstName());
            HelperUtils.setInputValue(signUpForm, "lastName", dto.getLastName());
            HelperUtils
            .setInputValue(signUpForm, "childName", dto.getChildName() == null ? "Aliah" : dto.getChildName());

            final WebElement childAgeBox = signUpForm.findElement(By.id("childAge"));
            final Select clickThis = new Select(childAgeBox);
            clickThis.selectByVisibleText(dto.getChildAge() == null ? "10" : dto.getChildAge());

            final Adress adress = dto.getAdress();
            assertNotNull(adress);
            HelperUtils.setInputValue(signUpForm, "street", adress.getStreet());
            HelperUtils.setInputValue(signUpForm, "houseNr", adress.getHouseNr());
            HelperUtils.setInputValue(signUpForm, "zipcode", adress.getZipcode());
            HelperUtils.setInputValue(signUpForm, "city", adress.getZipcode());
            HelperUtils.setInputValue(signUpForm, "phonenumber", dto.getPhoneNumber());

            HelperUtils.setInputValue(signUpForm, "userName", dto.getUserName());
            HelperUtils.setInputValue(signUpForm, "email", dto.getEmail());
            HelperUtils.setInputValue(signUpForm, "password", dto.getPassword());
            HelperUtils.setInputValue(signUpForm, "confirmPassword", dto.getPassword());

            final WebElement singUpBtn = wait.until(new VisibilityFunction(By.id("singUpBtn")));
            singUpBtn.click();

            Thread.sleep(1000);

            // TODO prüfe Felder in der Datenbank

        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }
}
