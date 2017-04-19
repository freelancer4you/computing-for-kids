package de.goldmann.apps.root.dto;

import static de.goldmann.apps.root.UIConstants.DATE_FORMAT;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.model.GoogleAccount;
import de.goldmann.apps.root.model.PostAdress;
import de.goldmann.apps.root.model.UserId;

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

    public CourseParticipantDto(final Course course, final UserId account, final CourseParticipant courseParticipant) {

        courseId = course.getId();
        coursePrice = String.valueOf(course.getPrice());
        if (account instanceof DefaultAccount)
        {
            firstName = ((DefaultAccount) account).getFirstName();
            salutation = ((DefaultAccount) account).getSalutation();
            lastName = ((DefaultAccount) account).getLastName();
        }
        else if (account instanceof GoogleAccount)
        {
            firstName = ((GoogleAccount) account).getGivenName();
            salutation = "male".equals(((GoogleAccount) account).getGender()) ? "Herr" : "Frau";
            lastName = ((GoogleAccount) account).getFamilyName();
        }
        userMail = account.getEmail();

        if (account instanceof DefaultAccount)
        {
            childAge = ((DefaultAccount) account).getChildAge();
            childName = ((DefaultAccount) account).getChildName();
        }
        courseName = course.getName();
        subscriptionDate = formatter.format(courseParticipant.getSubscriptionDate());
        registrationDate = formatter.format(account.getRegistrationDate());
        final Date begin = course.getBegin();
        courseBegin = formatter.format(begin);
        courseEnd = formatter.format(course.getEnd());
        if (account instanceof DefaultAccount)
        {
            final PostAdress adresse = ((DefaultAccount) account).getAdresse();
            city = adresse.getCity();
            houseNr = adresse.getHouseNr();
            zipcode = adresse.getZipcode();
            street = adresse.getStreet();
        }
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
