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
    private String            appointments;

    @Column(name = "duration", nullable = true)
    private String duration;


    // JPA-Konstruktor
    CourseDetails() {
        super();
    }

    public CourseDetails(final String curriculum, final String appointments, final String duration) {
        this.curriculum = curriculum;
        this.appointments = appointments;
        this.duration = duration;
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

}
