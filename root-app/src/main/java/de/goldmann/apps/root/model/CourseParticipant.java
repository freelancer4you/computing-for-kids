package de.goldmann.apps.root.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "course_participants")
public class CourseParticipant {

    @EmbeddedId
    private CourseParticipantPK id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription", nullable = false)
    private Date	subscriptionDate;

    @Column(name = "termagreed", nullable = true)
    private Boolean	termAgreed;

    @Column(name = "disclaimeragreed", nullable = true)
    private Boolean	disclaimerAgreed;

    @Column(name = "registrationtyp", nullable = false)
    private RegistrationTyp     registrationTyp;

    CourseParticipant() {
        super();
    }

    public CourseParticipant(final Course course, final UserId userId) {
        id = new CourseParticipantPK(course.getId(), userId.getEmail());
        registrationTyp = userId.getRegistrationTyp();
        final LocalDateTime ldt = LocalDateTime.now();
        final ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        subscriptionDate = Date.from(zdt.toInstant());
        disclaimerAgreed = Boolean.TRUE;
        termAgreed = Boolean.TRUE;
    }

    public String getCourse() {
        return id.getCourse();
    }

    public String getUserMail() {
        return id.getUserMail();
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public RegistrationTyp getRegistrationTyp() {
        return registrationTyp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final CourseParticipant other = (CourseParticipant) obj;
        if (id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        }
        else if (!id.equals(other.id))
        {
            return false;
        }
        return true;
    }


}
