package org.campus.hws.dao;

import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;

import java.util.List;

public interface SolutionDao {
    List<Solution> findAll();

    void add(Solution solution);

    List<Solution> findByTaskName(TaskType taskType);
}
