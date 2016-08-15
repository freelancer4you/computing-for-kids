package de.goldmann.map;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.model.UserRole;
import de.goldmann.apps.root.test.utils.TestUtils;
import de.goldmann.apps.tests.helpers.HelperUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class LoginTest extends WebTest {

    private UserDTO dto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        dto = TestUtils.buildUserDto();
    }

    @Test
    public void testLoginAsUser() {

        final User user = new User(dto);
        userRepository.save(user);

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {
            driver.get(HOST_ADRESS);

            login(wait, this.dto);

            Thread.sleep(1000);

            assertNotNull(driver.findElement(By.id("totalClicks")));
            assertNotNull(driver.findElement(By.id("totalImpressions")));
            assertNotNull(driver.findElement(By.id("totalCtr")));
            assertNotNull(driver.findElement(By.id("totalCpm")));
            assertNotNull(driver.findElement(By.id("fromDate")));

            Thread.sleep(1000);

            logout(wait);
        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testLoginAsAdmin() {

        final User user = new User(dto);
        userRepository.save(user);

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {
            driver.get(HOST_ADRESS);

            login(wait, adminDto());
            HelperUtils.analyzeLog(driver);
            Thread.sleep(1000);

            assertNotNull(driver.findElement(By.id("usersTable")));

            Thread.sleep(1000);

            logout(wait);
            HelperUtils.analyzeLog(driver);
        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

    private UserDTO adminDto() {

        final UserDTO userDTO = new UserDTO("goldi", "goldi", "username", "goldi23@freenet.de",
                "$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.", "phone",
                new Adress("street", "plz", "city"), "2016-08-15 15:20");
        final User admin = new User(userDTO, UserRole.ADMIN);

        this.userRepository.save(admin);
        return userDTO;
    }

}
