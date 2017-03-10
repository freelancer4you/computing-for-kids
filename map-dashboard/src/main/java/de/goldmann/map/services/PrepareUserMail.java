package de.goldmann.map.services;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.model.GoogleAccount;
import de.goldmann.apps.root.model.Schedule;
import de.goldmann.apps.root.model.UserId;

@Service
public class PrepareUserMail {

    public String prepare(final UserId userId, final Course course) throws IOException {

        final URL url = Resources.getResource("registrationMail.html");
        final String templateContent = Resources.toString(url, Charsets.UTF_8);
        final Schedule schedule = course.getSchedule();
        final Date registrationDate = userId.getRegistrationDate();
        final SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        final Calendar cal = Calendar.getInstance();
        cal.setTime(registrationDate);
        cal.add(Calendar.DATE, 14);
        final Date maxZahlungsTag = cal.getTime();

        if (userId instanceof DefaultAccount) {
            final DefaultAccount user = (DefaultAccount) userId;

            final String salutation = user.getSalutation();
            final String salutationPrefix = salutation.equals("Herr") ? "geehrter" : "geehrte";
            return MessageFormat.format(
                    templateContent,
                    salutationPrefix + " " + user.getTitle() + " " + salutation,

                    StringEscapeUtils.escapeHtml4(user.getLastName()),
                    dayFormat.format(registrationDate),
                    timeFormat.format(registrationDate),
                    StringEscapeUtils.escapeHtml4(user.getFirstName()),
                    StringEscapeUtils.escapeHtml4(user.getLastName()),
                    StringEscapeUtils.escapeHtml4(user.getChildName()),
                    StringEscapeUtils.escapeHtml4(user.getAdresse().getStreet()),
                    user.getAdresse().getHouseNr(),
                    user.getAdresse().getZipcode(),
                    StringEscapeUtils.escapeHtml4(user.getAdresse().getCity()),
                    user.getEmail(),
                    user.getChildAge(), // 12 in html
                    course.getPrice(), // 13 in html
                    dayFormat.format(maxZahlungsTag), // 14 in html
                    course.getId(), // 15 in html
                    StringEscapeUtils.escapeHtml4(course.getName()),
                    dayFormat.format(schedule.getBegin()) + " " + timeFormat.format(schedule.getBegin()),
                    StringEscapeUtils.escapeHtml4(course.getPlace()));
        } else if (userId instanceof GoogleAccount) {
            final GoogleAccount acc = (GoogleAccount) userId;
            String salutation = "male".equals(acc.getGender()) ? "Herr" : "Frau";
            String salutationPrefix = "male".equals(acc.getGender()) ? "geehrter" : "geehrte";
            if(StringUtils.isEmpty(acc.getGender())){
                salutation ="";
                salutationPrefix = "";
            }

            return MessageFormat.format(
                    templateContent,
                    salutationPrefix + " " + salutation,

                    StringEscapeUtils.escapeHtml4(acc.getFamilyName()),
                    dayFormat.format(registrationDate),
                    timeFormat.format(registrationDate),
                    StringEscapeUtils.escapeHtml4(acc.getGivenName()),
                    StringEscapeUtils.escapeHtml4(acc.getFamilyName()),
                    "",
                    "",
                    "",
                    "",
                    "",
                    acc.getEmail(),
                    "", // 12 in html
                    course.getPrice(), // 13 in html
                    dayFormat.format(maxZahlungsTag), // 14 in html
                    course.getId(), // 15 in html
                    StringEscapeUtils.escapeHtml4(course.getName()),
                    dayFormat.format(schedule.getBegin()) + " " + timeFormat.format(schedule.getBegin()),
                    StringEscapeUtils.escapeHtml4(course.getPlace())
                    );

        } else {
            throw new IllegalArgumentException("Unknown type of parameter 'userId': " + userId.getClass());
        }

    }
}
