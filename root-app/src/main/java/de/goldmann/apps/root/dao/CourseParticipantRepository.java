package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.CourseParticipant;
import de.goldmann.apps.root.model.CourseParticipantPK;

public interface CourseParticipantRepository extends JpaRepository<CourseParticipant, CourseParticipantPK> {

}
