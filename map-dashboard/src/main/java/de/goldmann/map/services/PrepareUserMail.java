package de.goldmann.map.services;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.Schedule;
import de.goldmann.apps.root.model.User;

@Service
public class PrepareUserMail {

    public String prepare(final User user, final Course course) throws IOException {
        final URL url = Resources.getResource("registrationMail.html");
        final String templateContent = Resources.toString(url, Charsets.UTF_8);
        final Schedule schedule = course.getSchedules().iterator().next();
        final Date registrationDate = user.getRegistrationDate();
        final SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        final String salutation = user.getSalutation();
        final String salutationPrefix = salutation.equals("Herr") ? "geehrter" : "geehrte";
        return MessageFormat.format(
                templateContent,
                salutationPrefix + " " + user.getTitle() + " " + salutation,
                user.getLastName(),
                dayFormat.format(registrationDate),
                timeFormat.format(registrationDate),
                user.getFirstName(),
                user.getLastName(),
                user.getChildName(),
                user.getAdresse().getStreet(),
                user.getAdresse().getHouseNr(),
                user.getAdresse().getZipcode(),
                user.getAdresse().getCity(),
                user.getEmail(),
                user.getChildAge(),
                course.getName(),
                schedule.getBegin(),
                course.getPlace());
    }
}
