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
import de.goldmann.apps.root.dto.CourseParticipantDto;
import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.services.VisitorsCounter;

@RestController
public class AdminAreaController {

	private final CourseParticipantRepository	courseParticipantRepository;

	private final VisitorsCounter  visitorsCounter;

	@Autowired
	public AdminAreaController(final CourseParticipantRepository courseParticipantRepository,
			final VisitorsCounter visitorsCounter) {
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
			users.add(new CourseParticipantDto(courseParticipant));
		}
		return users;
	}
}
