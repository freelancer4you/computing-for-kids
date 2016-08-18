package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.Course;

public interface CourseRepository extends JpaRepository<Course, String> {

}
