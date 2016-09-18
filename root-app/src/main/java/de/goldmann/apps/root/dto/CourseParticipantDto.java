package de.goldmann.apps.root.dto;

import static de.goldmann.apps.root.UIConstants.DATE_FORMAT;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.Schedule;
import de.goldmann.apps.root.model.User;

public class CourseParticipantDto implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String					subscriptionDate;
	private String				courseEnd;
	private String				courseBegin;
	private String					courseName;
	private String					childName;
	private String					childAge;
	private String					userMail;
	private String					lastName;
	private String					firstName;
	private String				registrationDate;

	@JsonIgnore
	private final SimpleDateFormat	formatter			= new SimpleDateFormat(DATE_FORMAT);

	public CourseParticipantDto() {
	}

	public CourseParticipantDto(final CourseParticipant courseParticipant) {

		final Course course = courseParticipant.getCourse();
		final User user = courseParticipant.getUserMail();
		firstName = user.getFirstName();
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

}
