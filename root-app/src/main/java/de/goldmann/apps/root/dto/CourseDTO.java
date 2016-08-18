package de.goldmann.apps.root.dto;

import java.io.Serializable;

import de.goldmann.apps.root.model.Level;

public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 7592980257189377674L;

    private String            id;

    private String name;

    private String icon;

    private String description;

    private Level level;

    private ScheduleDTO       schedule;

    private double price;

    private String            requirements;

    private String            place;

    CourseDTO() {
        super();
    }

    public CourseDTO(final String id, final String name, final String icon, final String description,
            final Level level, final double price, final String place, final String requirements,
            final ScheduleDTO schedule) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.level = level;
        this.price = price;
        this.place = place;
        this.requirements = requirements;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public ScheduleDTO getSchedule() {
        return schedule;
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

    public double getPrice() {
        return price;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getPlace() {
        return place;
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
        final CourseDTO other = (CourseDTO) obj;
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
        return "CourseDTO [" + (id != null ? "id=" + id + ", " : "") + (name != null ? "name=" + name + ", " : "")
                + (icon != null ? "icon=" + icon + ", " : "")
                + (description != null ? "description=" + description + ", " : "")
                + (level != null ? "level=" + level + ", " : "")
                + (schedule != null ? "schedule=" + schedule + ", " : "") + "price=" + price + ", "
                + (requirements != null ? "requirements=" + requirements + ", " : "")
                + (place != null ? "place=" + place : "") + "]";
    }

}