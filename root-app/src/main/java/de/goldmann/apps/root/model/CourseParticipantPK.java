package de.goldmann.apps.root.model;

import java.io.Serializable;

public class CourseParticipantPK implements Serializable {

	private static final long serialVersionUID = -752099568548436106L;

	private String course;

	private String userId;

	CourseParticipantPK() {
		super();
	}

	/**
	 * @param course
	 *            der Name des Kurses, an dem ein {@link User} teilnimmt
	 * @param mail
	 *            die Email-Adresse des {@link User}
	 */
	public CourseParticipantPK(final String course, final String userMail) {
		this.course = course;
		this.userId = userMail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (course == null ? 0 : course.hashCode());
		result = prime * result + (userId == null ? 0 : userId.hashCode());
		return result;
	}

	public String getCourse() {
		return course;
	}

	public String getUserMail() {
		return userId;
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
		final CourseParticipantPK other = (CourseParticipantPK) obj;
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
		return "CourseParticipantPK [course=" + course + ", email=" + userId + "]";
	}
}
