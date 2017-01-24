package de.goldmann.map.controller;

import static de.goldmann.apps.root.UIConstants.LIST_USERS_REQUEST_PATH;
import static de.goldmann.apps.root.UIConstants.VISITORS_COUNT_REQUEST_PATH;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dao.DefaultAccountRepository;
import de.goldmann.apps.root.dao.GoogleAccountRepository;
import de.goldmann.apps.root.dto.CourseParticipantDto;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.RegistrationTyp;
import de.goldmann.apps.root.model.UserId;
import de.goldmann.apps.root.services.VisitorsCounter;

@RestController
public class AdminAreaController {

    private final CourseParticipantRepository	courseParticipantRepository;
    private final CourseRepository            courseRepo;
    private final VisitorsCounter  visitorsCounter;
    private GoogleAccountRepository           googleAccountRepository;
    private DefaultAccountRepository          defaultAccountRepository;

    @Autowired
    public AdminAreaController(final CourseParticipantRepository courseParticipantRepository,
            final VisitorsCounter visitorsCounter, final CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
        this.courseParticipantRepository = Objects.requireNonNull(courseParticipantRepository,
                "courseParticipantRepository");
        this.visitorsCounter = Objects.requireNonNull(visitorsCounter, "visitorsCounter");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = VISITORS_COUNT_REQUEST_PATH, method = RequestMethod.GET)
    public int visitorsCount() {
        return visitorsCounter.getCounter();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = LIST_USERS_REQUEST_PATH, method = RequestMethod.GET)
    public List<CourseParticipantDto> listUsers() {

        final List<CourseParticipantDto> users = new ArrayList<>();

        final List<CourseParticipant> participants = courseParticipantRepository.findAll();
        for (final CourseParticipant courseParticipant : participants) {

            final RegistrationTyp registrationType = courseParticipant.getRegistrationTyp();
            UserId userId;
            switch (registrationType)
            {
                case DEFAULTACCOUNT:
                    userId = defaultAccountRepository.findByEmail(courseParticipant.getUserMail());
                    break;
                case GOOGLEACCOUNT:
                    userId = googleAccountRepository.findByEmail(courseParticipant.getUserMail());
                    break;
                default:
                    throw new IllegalArgumentException("Unrecognized registrationType:" + registrationType);
            }

            users.add(
                    new CourseParticipantDto(courseRepo.findOne(courseParticipant.getCourse()),
                            userId,
                            courseParticipant));
        }
        return users;
    }
}
