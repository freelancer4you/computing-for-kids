package de.goldmann.map.controller;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.map.UiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest
public class CourseControllerTest extends WebTest {

	private static final String COURSES_KIDS_PATH = "/#/courses/kids";

	@Test
	public void testListCourses() {

		final FluentWait<WebDriver> wait = setupFluentWait(driver);
		try {
			driver.get(HOST_ADRESS + COURSES_KIDS_PATH);

			// login(wait, TestUtils.buildUserDto());

			// Thread.sleep(1000);
			//
			// assertNotNull(driver.findElement(By.id("totalClicks")));
			// assertNotNull(driver.findElement(By.id("totalImpressions")));
			// assertNotNull(driver.findElement(By.id("totalCtr")));
			// assertNotNull(driver.findElement(By.id("totalCpm")));
			// assertNotNull(driver.findElement(By.id("fromDate")));

			Thread.sleep(1000);

			logout(wait);
		}
		catch (final Exception e) {
			fail(e.getMessage());
		}
	}

}
