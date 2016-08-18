package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "begindate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date begin;

    @Column(name = "enddate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    // private String[] days;

    Schedule() {
    }

    // public Schedule(Date begin, Date end, String... days) {
    public Schedule(final Date begin, final Date end) {
        this.begin = begin;
        this.end = end;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(final Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (begin == null ? 0 : begin.hashCode());
        result = prime * result + (end == null ? 0 : end.hashCode());
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
        final Schedule other = (Schedule) obj;
        if (begin == null) {
            if (other.begin != null) {
                return false;
            }
        }
        else if (!begin.equals(other.begin)) {
            return false;
        }
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        }
        else if (!end.equals(other.end)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Schedule [" + (begin != null ? "begin=" + begin + ", " : "") + (end != null ? "end=" + end : "") + "]";
    }

    // public String[] getDays() {
    // return days;
    // }
    //
    // public void setDays(String[] days) {
    // this.days = days;
    // }



}
