package org.campus.hws.dao;

import org.campus.hws.entity.Solution;

import java.util.List;

public interface SolutionDao {
    List<Solution> findAll();

    void add(Solution solution);

    List<Solution> filterByTask(String filter);
}
