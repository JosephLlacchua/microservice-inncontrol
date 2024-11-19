package com.inncontrol.task.infrastructure.persistence.jpa.repositories;

import com.inncontrol.task.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
