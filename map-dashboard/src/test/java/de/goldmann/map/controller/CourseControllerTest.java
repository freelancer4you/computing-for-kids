package de.goldmann.map.controller;

import static de.goldmann.apps.root.UIConstants.COURSES_KIDS_PATH;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.tests.helpers.VisibilityFunction;
import de.goldmann.map.UiApplication;
import de.goldmann.map.WebTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class CourseControllerTest extends WebTest {

    private static final String COURSE_BANNER_SELECTOR = "#container > div.ng-scope > div > div > div > div > div.headerCourseBanner.ng-binding";
    private static final String COURSE_PRICE_SELECTOR  = "#container > div.ng-scope > div > div:nth-child(6) > div > div > div.clearfixHeader > div:nth-child(3) > p";
    private static final String COURSE_ICON_SELECTOR   = "#container > div.ng-scope > div > div:nth-child(6) > div > div > div.clearfixHeader > div:nth-child(1) > img";

    @Autowired
    private CourseRepository    courseRepo;

    @Test
    @Sql("testListCourses.sql")
    public void testListCourses() {

        final List<Course> findAll = courseRepo.findAll();
        assertEquals(1, findAll.size());
        final Course existingCourse = findAll.get(0);
        assertNotNull(existingCourse);

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {
            driver.get(HOST_ADRESS + COURSES_KIDS_PATH);

            final WebElement courseBannerElement = wait
                    .until(new VisibilityFunction(By.cssSelector(COURSE_BANNER_SELECTOR)));
            assertEquals(existingCourse.getName(), courseBannerElement.getText());

            final WebElement coursePriceElement = driver.findElement(By.cssSelector(COURSE_PRICE_SELECTOR));
            assertEquals(
                    String.valueOf(existingCourse.getPrice()),
                    coursePriceElement.getText().replaceAll("€", "").trim());

            final WebElement courseIconElement = driver.findElement(By.cssSelector(COURSE_ICON_SELECTOR));
            System.out.println(courseIconElement.getAttribute("ng-src"));

            assertEquals("img/" + existingCourse.getIcon(), courseIconElement.getAttribute("ng-src"));

            Thread.sleep(1000);

        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

}
