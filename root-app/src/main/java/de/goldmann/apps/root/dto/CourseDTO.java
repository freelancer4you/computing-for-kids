package de.goldmann.apps.root.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import de.goldmann.apps.root.model.Level;

public class CourseDTO implements Serializable {

    private static final long serialVersionUID = 7592980257189377674L;

    private String name;

    private String icon;

    private String description;

    private boolean open;

    private Level level;

    private Set<ScheduleDTO>	schedules;

    private double price;

    private String            requirements;

    CourseDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CourseDTO(final String name, final String icon, final String description, final boolean open, final Level level, final double price) {
        super();
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.open = open;
        this.level = level;
        this.price = price;
        schedules = new HashSet<>();
    }

    public Set<ScheduleDTO> getSchedules() {
        return schedules;
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

    public double getPrice() {
        return price;
    }

    public String getRequirements() {
        return requirements;
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
        final CourseDTO other = (CourseDTO) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CourseDTO [" + (name != null ? "name=" + name + ", " : "") + (icon != null ? "icon=" + icon + ", " : "")
                + (description != null ? "description=" + description + ", " : "") + "open=" + open + ", "
                + (level != null ? "level=" + level + ", " : "")
                + (schedules != null ? "schedules=" + schedules + ", " : "") + "price=" + price + ", "
                + (requirements != null ? "requirements=" + requirements : "") + "]";
    }

}
