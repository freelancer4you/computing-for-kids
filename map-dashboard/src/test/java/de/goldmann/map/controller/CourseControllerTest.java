package de.goldmann.map.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

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
    private static final String COURSE_PRICE_SELECTOR  = "#container > div:nth-child(2) > div > div > div > div > div.clearfixHeader > div:nth-child(3) > table > tbody > tr:nth-child(1) > td > p";
    private static final String COURSE_ICON_SELECTOR   = "#container > div:nth-child(2) > div > div > div > div > div.clearfixHeader > div:nth-child(1) > img";

    @Autowired
    private CourseRepository    courseRepo;

    @Test
    @Sql("testListCourses.sql")
    public void testListCourses() {

        final List<Course> findAll = courseRepo.findAll();
        assertThat(findAll, hasSize(1));
        final Course existingCourse = findAll.get(0);

        final FluentWait<WebDriver> wait = setupFluentWait(driver);
        try {
            driver.get(HOST_ADRESS);

            kursSeiteOeffnen(wait);

            final WebElement courseBannerElement = wait
                    .until(new VisibilityFunction(By.cssSelector(COURSE_BANNER_SELECTOR)));
            assertThat(existingCourse.getName(), is(equalTo(courseBannerElement.getText())));

            final WebElement coursePriceElement = driver.findElement(By.cssSelector(COURSE_PRICE_SELECTOR));
            assertThat(
                    existingCourse.getPrice(),
                    is(equalTo(Double.valueOf(coursePriceElement.getText().replaceAll("€", "").trim()))));

            final WebElement courseIconElement = driver.findElement(By.cssSelector(COURSE_ICON_SELECTOR));
            assertThat("img/" + existingCourse.getIcon(), is(equalTo(courseIconElement.getAttribute("ng-src"))));

            Thread.sleep(1000);

        }
        catch (final Exception e) {
            fail(e.getMessage());
        }
    }

}
