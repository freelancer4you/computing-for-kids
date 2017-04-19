package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {

    private static final long	serialVersionUID	= 1L;

    private String begin;

    private String end;

    private String timeOfDay;

    ScheduleDTO() {
        super();
    }

    public ScheduleDTO(final String begin, final String end, final String timeOfDay) {
        this.begin = begin;
        this.end = end;
        this.timeOfDay = timeOfDay;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    @Override
    public String toString() {
        return "ScheduleDTO [" + (begin != null ? "begin=" + begin + ", " : "") + (end != null ? "end=" + end : "")
                + "]";
    }

}
