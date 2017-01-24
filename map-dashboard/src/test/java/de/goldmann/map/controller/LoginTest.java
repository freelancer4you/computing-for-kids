package de.goldmann.map.controller;

import static org.assertj.core.api.Assertions.fail;
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

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.DefaultAccountDTO;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.model.UserRole;
import de.goldmann.apps.root.test.utils.TestUtils;
import de.goldmann.apps.tests.helpers.HelperUtils;
import de.goldmann.map.UiApplication;
import de.goldmann.map.WebTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class LoginTest extends WebTest {

    private DefaultAccountDTO dto;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        dto = TestUtils.buildUserDto();
    }

    @Test
    public void testLoginAsUser() {

        final DefaultAccount user = new DefaultAccount(dto);
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

    @Autowired
    private CourseRepository courseRepo;

    @Test
    public void testLoginAsAdmin() {


        // Set<Schedule> schedules = new HashSet<>();
        // Schedule schedule = new Schedule(new Date(), new Date());
        // scheduleRepository.save(schedule);
        // schedules.add(schedule);
        // Course course = new Course("name", "icon", "description",
        // Level.Beginner, 5.0);
        // this.courseRepo.save(course);
        // this.courseRepo.findByName("name").getSchedules().add(schedule);
        // this.courseRepo.save(course);
        //
        // List<Course> courses = this.courseRepo.findAll();
        // for (Course c : courses) {
        //
        // System.out.println(c);
        // System.out.println(c.getSchedules());
        //
        // }


        final DefaultAccount user = new DefaultAccount(dto);
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

    private DefaultAccountDTO adminDto() {

        final DefaultAccountDTO userDTO = new DefaultAccountDTO("Herr", null, "goldi", "lastName", "goldi23@freenet.de",
                "$2a$10$kElbYwnGCPrd3ogjEN8wVOuJ/xCuz.FrnoHigLydnE0U2qsmGE4v.", "phone",
                new Adress("street", "plz", "city", "8"), "2016-08-15 15:20", "Ali", "10");
        final DefaultAccount admin = new DefaultAccount(userDTO, UserRole.ADMIN);

        this.userRepository.save(admin);
        return userDTO;
    }

}
