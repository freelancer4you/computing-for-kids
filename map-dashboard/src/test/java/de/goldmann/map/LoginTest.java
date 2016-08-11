package de.goldmann.map;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.UserRepository;
import de.goldmann.apps.root.dto.NewUserDTO;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.test.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class LoginTest extends WebTest {

    @Autowired
    private UserRepository userRepository;

    private NewUserDTO dto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        userRepository.deleteAll();
        dto = TestUtils.buildUserDto();
        final User user = new User(dto);
        userRepository.save(user);
    }

    @Test
    public void testLogin() throws Exception {

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        driver.get(HOST_ADRESS);

        login(wait, this.dto);

        Thread.sleep(1000);

        assertNotNull(driver.findElement(By.id("totalClicks")));
        assertNotNull(driver.findElement(By.id("totalImpressions")));
        assertNotNull(driver.findElement(By.id("totalCtr")));
        assertNotNull(driver.findElement(By.id("totalCpm")));
        assertNotNull(driver.findElement(By.id("fromDate")));

        logout(wait);
    }
}
