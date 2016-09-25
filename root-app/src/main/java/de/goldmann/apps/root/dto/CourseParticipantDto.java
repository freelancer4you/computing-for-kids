package de.goldmann.apps.root.dto;

import static de.goldmann.apps.root.UIConstants.DATE_FORMAT;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.PostAdress;
import de.goldmann.apps.root.model.Schedule;
import de.goldmann.apps.root.model.User;

public class CourseParticipantDto implements Serializable {

	private static final long		serialVersionUID	= 1L;

	private String					subscriptionDate;
	private String					courseId;
	private String					courseEnd;
	private String					courseBegin;
	private String					courseName;
	private String					coursePrice;
	private String					childName;
	private String					childAge;
	private String					userMail;
	private String					salutation;
	private String					lastName;
	private String					firstName;
	private String					street;
	private String					city;
	private String					registrationDate;

	@JsonIgnore
	private final SimpleDateFormat	formatter			= new SimpleDateFormat(DATE_FORMAT);

	private String					houseNr;

	private String					zipcode;

	public CourseParticipantDto() {
	}

	public CourseParticipantDto(final CourseParticipant courseParticipant) {

		final Course course = courseParticipant.getCourse();
		final User user = courseParticipant.getUserMail();
		courseId = course.getId();
		// coursePrice = course.getPrice();
		firstName = user.getFirstName();
		salutation = user.getSalutation();
		lastName = user.getLastName();
		userMail = user.getEmail();
		childAge = user.getChildAge();
		childName = user.getChildName();
		courseName = course.getName();
		subscriptionDate = formatter.format(courseParticipant.getSubscriptionDate());
		registrationDate = formatter.format(user.getRegistrationDate());
		final Schedule schedule = course.getSchedule();
		courseBegin = formatter.format(schedule.getBegin());
		courseEnd = formatter.format(schedule.getEnd());
		final PostAdress adresse = user.getAdresse();
		city = adresse.getCity();
		houseNr = adresse.getHouseNr();
		zipcode = adresse.getZipcode();
		street = adresse.getStreet();
	}

	public static void main(final String[] args) {
		final String result = String.format("%1$,.2f", 10.1);
		System.out.println(result);
		System.out.println(String.format("%s", 10.1));
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getChildName() {
		return childName;
	}

	public String getChildAge() {
		return childAge;
	}

	public String getUserMail() {
		return userMail;
	}

	public String getSalutation() {
		return salutation;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public String getCourseEnd() {
		return courseEnd;
	}

	public String getCourseBegin() {
		return courseBegin;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public String getHouseNr() {
		return houseNr;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCourseId() {
		return courseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCoursePrice() {
		return coursePrice;
	}

}
