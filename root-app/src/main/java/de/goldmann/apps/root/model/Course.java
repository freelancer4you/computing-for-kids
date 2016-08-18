package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    private static final long serialVersionUID = 6500153552902318705L;

    @Id
    private String name;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "description", nullable = false)
    @Lob
    private String description;

    // just for accordion
    @Transient
    private boolean open;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Schedule> schedules;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "requirements", nullable = true)
    @Lob
    private String            requirements;

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

    public boolean isOpen() {
        return open;
    }

    public Level getLevel() {
        return level;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public double getPrice() {
        return price;
    }

    public String getRequirements() {
        return requirements;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course [" + (name != null ? "name=" + name + ", " : "") + (icon != null ? "icon=" + icon + ", " : "")
                + (description != null ? "description=" + description + ", " : "") + "open=" + open + ", "
                + (level != null ? "level=" + level + ", " : "")
                + (schedules != null ? "schedules=" + schedules + ", " : "") + "price=" + price + ", "
                + (requirements != null ? "requirements=" + requirements : "") + "]";
    }

}
