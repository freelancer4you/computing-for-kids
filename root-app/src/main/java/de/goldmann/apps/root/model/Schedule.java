package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "begindate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date begin;

	@Column(name = "enddate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;

	// private String[] days;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COURSE_NAME")
	private Course course;

	Schedule() {
	}

	// public Schedule(Date begin, Date end, String... days) {
	public Schedule(Date begin, Date end) {
		UUID idOne = UUID.randomUUID();
		this.id = idOne.getMostSignificantBits();
		this.begin = begin;
		this.end = end;
		// this.days = days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	// public String[] getDays() {
	// return days;
	// }
	//
	// public void setDays(String[] days) {
	// this.days = days;
	// }

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [" + (begin != null ? "begin=" + begin + ", " : "") + (end != null ? "end=" + end + ", " : "")
				+ (course != null ? "course=" + course : "") + "]";
	}

}
