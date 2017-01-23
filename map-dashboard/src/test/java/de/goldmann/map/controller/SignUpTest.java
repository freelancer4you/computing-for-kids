package de.goldmann.map.controller;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.CourseParticipantPK;
import de.goldmann.apps.root.model.DefaultAccount;
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

	private static final String			SELCTOR_REGISTER_BTN	= "#container > div:nth-child(2) > div > div:nth-child(5) > div > div > div.clearfixHeader > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(3) > a";

	private final static String			SELECT_COURSES_LINK			= "#navbar > ul:nth-child(1) > li.dropdown > a";

	private static final String			SELCTOR_KIDS_COURSE_LINK	= "#navbar > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(1) > a";

	@Autowired
	private CourseParticipantRepository courseParticipantRepository;

	@Autowired
	private CourseRepository			courseRepo;

	@Test
	@Sql("testSignUp.sql")
	public void test() {

		final FluentWait<WebDriver> wait = setupFluentWait(driver);
		try {

			assertEquals(1, courseRepo.findAll().size());

			driver.get(HOST_ADRESS);

			Thread.sleep(2000);
			final WebElement courseLink = wait.until(new VisibilityFunction(By.cssSelector(SELECT_COURSES_LINK)));
			courseLink.click();

			final WebElement kidsCourseLink = wait
					.until(new VisibilityFunction(By.cssSelector(SELCTOR_KIDS_COURSE_LINK)));
			kidsCourseLink.click();

			final WebElement registerBtn = wait.until(new VisibilityFunction(By.cssSelector(SELCTOR_REGISTER_BTN)));
			registerBtn.click();

			final UserDTO dto = TestUtils.buildUserDto();

			final WebElement signUpForm = wait.until(new VisibilityFunction(By.id("signUpForm")));

			final WebElement salutationBox = signUpForm.findElement(By.id("salutation"));
			final Select salutationBoxSelect = new Select(salutationBox);
			salutationBoxSelect.selectByVisibleText(dto.getSalutation());

			HelperUtils.setInputValue(signUpForm, "firstName", dto.getFirstName());
			HelperUtils.setInputValue(signUpForm, "lastName", dto.getLastName());
			HelperUtils
			.setInputValue(signUpForm, "childName", dto.getChildName() == null ? "Aliah" : dto.getChildName());

			final WebElement childAgeBox = signUpForm.findElement(By.id("childAge"));
			final Select childAgeBoxSelect = new Select(childAgeBox);
			childAgeBoxSelect.selectByVisibleText(dto.getChildAge() == null ? "10" : dto.getChildAge());

			final Adress adress = dto.getAdress();
			assertNotNull(adress);
			HelperUtils.setInputValue(signUpForm, "street", adress.getStreet());
			HelperUtils.setInputValue(signUpForm, "houseNr", adress.getHouseNr());
			HelperUtils.setInputValue(signUpForm, "zipcode", adress.getZipcode());
			HelperUtils.setInputValue(signUpForm, "city", adress.getZipcode());
			HelperUtils.setInputValue(signUpForm, "phonenumber", dto.getPhoneNumber());

			final String userMail = dto.getEmail();
			HelperUtils.setInputValue(signUpForm, "email", userMail);

			final WebElement termAgreementBtn = wait.until(new VisibilityFunction(By.id("termAgreement")));
			termAgreementBtn.click();
			final WebElement disclaimerAggreement = wait.until(new VisibilityFunction(By.id("disclaimerAgreement")));
			disclaimerAggreement.click();

			final WebElement singUpBtn = wait.until(new VisibilityFunction(By.id("singUpBtn")));
			singUpBtn.click();

			Thread.sleep(5000);

			final DefaultAccount registeredUser = userRepository.findByEmail(userMail);
			assertNotNull("Der Benutzer sollte nicht null sein:", registeredUser);
			assertEquals(dto.getFirstName(), registeredUser.getFirstName());
			assertEquals(dto.getLastName(), registeredUser.getLastName());
			final CourseParticipant registrationEntry = courseParticipantRepository
					.findOne(new CourseParticipantPK("1", userMail));
			assertNotNull("Es sollte ein Registrierungseintrag vorhanden sein:", registrationEntry);
			HelperUtils.analyzeLog(driver);
		}
		catch (final Exception e) {
			fail(e.getMessage());
		}
	}
}
