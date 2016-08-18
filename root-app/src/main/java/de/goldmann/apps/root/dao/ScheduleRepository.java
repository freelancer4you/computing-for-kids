package de.goldmann.apps.root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.apps.root.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
