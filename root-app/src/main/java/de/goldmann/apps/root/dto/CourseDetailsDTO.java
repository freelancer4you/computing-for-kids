package de.goldmann.apps.root.dto;

import java.io.Serializable;
import java.util.Objects;

public class CourseDetailsDTO implements Serializable {

    private static final String COURSE_DETAILS_EXTENSION = ".html";

    private static final String COURSES_DETAILS_PATH     = "/partials/courses/details/";

    private static final long serialVersionUID = 1806333652496335136L;

    private final String				name;

    private final String				description;

    private final String      curriculum;

    private final String      appointments;

    private final String duration;

    public CourseDetailsDTO(final String name, final String description, final String curriculum,
            final String appointments, final String duration) {
        this.name = Objects.requireNonNull(name, "name");
        this.description = Objects.requireNonNull(description, "description");
        this.curriculum = COURSES_DETAILS_PATH + curriculum + COURSE_DETAILS_EXTENSION;
        this.appointments = COURSES_DETAILS_PATH + appointments + COURSE_DETAILS_EXTENSION;
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public String getAppointments() {
        return appointments;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "CourseDetailsDTO [" + (name != null ? "name=" + name : "") + "]";
    }

}
