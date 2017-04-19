package de.goldmann.apps.root.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseDetails implements Serializable {

    private static final long serialVersionUID = -7250736862925024161L;

    @Column(name = "curriculum", nullable = false)
    private String curriculum;

    @Column(name = "appointments", nullable = true)
    private String appointments;

    @Column(name = "duration_weeks", nullable = true)
    private int durationWeeks;

    @Column(name = "duration_desc", nullable = true)
    private String durationDesc;

    @Column(name = "timeofday", nullable = true)
    private String timeOfDay;

    // JPA-Konstruktor
    CourseDetails() {
        super();
    }

    public String getCurriculum() {
        return curriculum;
    }

    public String getAppointments() {
        return appointments;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public String getDurationDesc() {
        return durationDesc;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }
}
