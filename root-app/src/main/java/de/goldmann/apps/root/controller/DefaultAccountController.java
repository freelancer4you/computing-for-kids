package de.goldmann.apps.root.controller;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.UIConstants;
import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.root.dto.DefaultAccountDTO;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.DefaultAccount;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.UserActivityReport;

@RestController
@Transactional
public class DefaultAccountController {

    private static final Logger               LOGGER = LogManager.getLogger(DefaultAccountController.class);

    private final CourseParticipantRepository courseParticipantRepository;
    private final DefaultAccountRepository    accountRepository;
    private final UserActivityReport          activityReport;
    private final CourseRepository            courseRepo;

    @Autowired
    public DefaultAccountController(final DefaultAccountRepository accountRepository,
            final UserActivityReport activityReport,
            final CourseRepository courseRepo, final CourseParticipantRepository courseParticipantRepository) {
        this.accountRepository = Objects
                .requireNonNull(accountRepository, "Parameter 'accountRepository' darf nicht null sein.");
        this.activityReport = Objects
                .requireNonNull(activityReport, "Parameter 'activityReport' darf nicht null sein.");
        this.courseRepo = Objects.requireNonNull(courseRepo, "Parameter 'courseRepo' darf nicht null sein.");
        this.courseParticipantRepository = Objects
                .requireNonNull(
                        courseParticipantRepository,
                        "Parameter 'courseParticipantRepository' darf nicht null sein.");

    }

    @ResponseBody
    @RequestMapping(value = UIConstants.DEFAULT_REGISTRATION, method = RequestMethod.POST)
    public ResponseEntity<String> defaultRegistration(@RequestBody final String payload,
            @RequestParam("id") final String courseId)
                    throws Exception {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            final DefaultAccountDTO user = mapper.readValue(payload, DefaultAccountDTO.class);

            final String email = user.getEmail();
            if (accountRepository.findByEmail(email) != null)
            {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(
                                "{\"message\":\" Es existiert bereits ein Nutzer mit der Email-Adresse '"
                                        + email
                                        + "'.\"}");
            }
            else
            {
                final UserId storedUser = accountRepository.save(new DefaultAccount(user));
                final Course course = courseRepo.findOne(courseId);

                courseParticipantRepository.save(new CourseParticipant(course, storedUser));

                activityReport.registered(storedUser, course);
            }
        }
        catch (final Exception e) {
            LOGGER.error("Fehler bei Benutzerregistrierung:" + payload, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
        return ResponseEntity.ok().body("");
    }

}
