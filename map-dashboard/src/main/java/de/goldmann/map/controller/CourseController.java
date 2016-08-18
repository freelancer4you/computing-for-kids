package de.goldmann.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.goldmann.apps.root.dao.CourseRepository;
import de.goldmann.apps.root.model.Course;

@Controller
public class CourseController {

	@Autowired
	private CourseRepository courseRepo;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/listCourses", method = RequestMethod.GET)
	public List<Course> listCourses() {

		// -- Das führt zu einer Jackson-Exception
		// Eventuell mappen auf DTO
		List<Course> courses = this.courseRepo.findAll();
		// for (Course course : courses) {
		// try {
		// System.out.println(course);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		return courses;
	}
}
