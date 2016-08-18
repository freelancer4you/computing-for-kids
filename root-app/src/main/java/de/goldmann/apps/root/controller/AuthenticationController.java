package de.goldmann.apps.root.controller;

import java.security.Principal;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.goldmann.apps.root.dao.CourseParticipantRepository;
import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dto.UserDTO;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.User;
import de.goldmann.apps.root.services.UserActivityReport;
import de.goldmann.apps.root.services.UserService;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);

	private final CourseParticipantRepository courseParticipantRepository;
	private final UserService userService;
	private final UserActivityReport activityReport;
	private final CourseRepository   courseRepo;

	@Autowired
	public AuthenticationController(final UserService userService, final UserActivityReport activityReport,
			final CourseRepository courseRepo, final CourseParticipantRepository courseParticipantRepository) {
		this.userService = Objects.requireNonNull(userService, "Parameter 'userService' darf nicht null sein.");
		this.activityReport = Objects
				.requireNonNull(activityReport, "Parameter 'activityReport' darf nicht null sein.");
		this.courseRepo = Objects.requireNonNull(courseRepo, "Parameter 'courseRepo' darf nicht null sein.");
		this.courseParticipantRepository = Objects
				.requireNonNull(
						courseParticipantRepository,
						"Parameter 'courseParticipantRepository' darf nicht null sein.");

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> errorHandler(final Exception exc) {
		LOGGER.error(exc.getMessage(), exc);
		return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public void registerUser(@RequestBody final String payload, @RequestParam("id") final String courseId)
			throws Exception {
		final ObjectMapper mapper = new ObjectMapper();

		try {
			final UserDTO user = mapper.readValue(payload, UserDTO.class);
			final User storedUser = userService.createUser(user);
			final Course course = courseRepo.findOne(courseId);

			courseParticipantRepository.save(new CourseParticipant(course, storedUser));

			activityReport.registered(storedUser, course);
		}
		catch (final Exception e) {
			// TODO unzureichende Fehlerbehandlung
			LOGGER.error("Fehler bei Benutzerregistrierung:" + payload, e);
			throw e;
		}

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public Principal user(final Principal user) {
		return user;
	}
}
