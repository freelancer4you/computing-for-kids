package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "courses")
// @Cache(region = "coursesCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 6500153552902318705L;

    @Id
    private String            id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    @Column(name = "begindate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "requirements", nullable = true)
    @Lob
    private String            requirements;

    @Column(name = "place", nullable = false)
    @Lob
    private String            place;

    @Embedded
    private CourseDetails     details;

    Course() {
    }

    public Course(final String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public Course(final String name, final String icon, final String description, final Level level, final double price) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.level = level;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public Level getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public double getPrice() {
        return price;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getPlace() {
        return place;
    }

    public Date getBegin() {
        return begin;
    }

    public CourseDetails getDetails() {
        return details;
    }

    public void setDescription(final String description) {
        this.description = description;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course ["
                + (name != null ? "name=" + name + ", " : "")
                + (icon != null ? "icon=" + icon + ", " : "")
                + (description != null ? "description=" + description + ", " : "")
                + (level != null ? "level=" + level + ", " : "")
                + (begin != null ? "begin=" + begin + ", " : "")
                + "price="
                + price
                + ", "
                + (requirements != null ? "requirements=" + requirements + ", " : "")
                + (place != null ? "place=" + place + ", " : "")
                + (details != null ? "details=" + details : "")
                + "]";
    }

    public Date getEnd() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);
        calendar.add(Calendar.WEEK_OF_YEAR, getDetails().getDurationWeeks());
        return calendar.getTime();
    }

}
