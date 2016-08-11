package de.goldmann.apps.root.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    private static final long serialVersionUID = 6500153552902318705L;

    @Id
    private String name;

    private double costs;

    private String period;

    private int hours;

    private String shortDescription;

    private int maxParticipant;


    Course() {
        super();
    }

    public Course(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getCosts() {
        return costs;
    }

    public String getPeriod() {
        return period;
    }

    public int getHours() {
        return hours;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
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
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course [name=" + name + "]";
    }

}
