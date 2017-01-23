package de.goldmann.apps.root.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "course_participants")
@IdClass(CourseParticipantPK.class)
public class CourseParticipant {

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private UserId  userId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscription", nullable = false)
    private Date	subscriptionDate;

    @Column(name = "termagreed", nullable = true)
    private Boolean	termAgreed;

    @Column(name = "disclaimeragreed", nullable = true)
    private Boolean	disclaimerAgreed;

    CourseParticipant() {
        super();
    }

    public CourseParticipant(final Course course, final UserId userId) {
        this.course = course;
        this.userId = userId;
        final LocalDateTime ldt = LocalDateTime.now();
        final ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        subscriptionDate = Date.from(zdt.toInstant());
        disclaimerAgreed = Boolean.TRUE;
        termAgreed = Boolean.TRUE;
    }

    public Course getCourse() {
        return course;
    }

    public UserId getUserId() {
        return userId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (course == null ? 0 : course.hashCode());
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CourseParticipant other = (CourseParticipant) obj;
        if (course == null) {
            if (other.course != null) {
                return false;
            }
        }
        else if (!course.equals(other.course)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        }
        else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseParticipant [course=" + course + ", user=" + userId + "]";
    }

}
