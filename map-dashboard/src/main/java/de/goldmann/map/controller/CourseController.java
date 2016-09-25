package de.goldmann.map.controller;

import static de.goldmann.apps.root.UIConstants.COURSES_DETAILS_REQUEST_PATH;
import static de.goldmann.apps.root.UIConstants.COURSES_REQUEST_PATH;
import static de.goldmann.apps.root.UIConstants.COURSE_DATE_FORMAT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.dto.CourseDTO;
import de.goldmann.apps.root.dto.CourseDetailsDTO;
import de.goldmann.apps.root.dto.ScheduleDTO;
import de.goldmann.apps.root.model.Course;
import de.goldmann.apps.root.model.CourseDetails;
import de.goldmann.apps.root.model.Schedule;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepo;

    private final SimpleDateFormat	formatter	= new SimpleDateFormat(COURSE_DATE_FORMAT);

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = COURSES_REQUEST_PATH, method = RequestMethod.GET)
    public List<CourseDTO> listCourses() {

        final List<CourseDTO> courses = new ArrayList<>();
        for (final Course course : courseRepo.findAll()) {
            final Schedule schedule = course.getSchedule();
            final CourseDTO courseDTO = new CourseDTO(course.getId(),
                    course.getName(),
                    course.getIcon(),
                    course.getDescription(),
                    course.getLevel(),
                    course.getPrice(),
                    course.getPlace(), course.getRequirements(),
                    new ScheduleDTO(formatter.format(schedule.getBegin()), formatter.format(schedule.getEnd())));

            courses.add(courseDTO);
        }
        return courses;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = COURSES_DETAILS_REQUEST_PATH, method = RequestMethod.GET)
    public CourseDetailsDTO courseDetails(@RequestParam("id") final String name) {
        final Course course = courseRepo.findOne(name);
        final CourseDetails courseDetails = course.getDetails();

        return new CourseDetailsDTO(course.getName(), course.getDescription(), courseDetails.getCurriculum(),
                courseDetails.getAppointments(), courseDetails.getDuration());
    }
}
