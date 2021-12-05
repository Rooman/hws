package org.campus.hws.service;

import org.campus.hws.dao.SolutionDao;
import org.campus.hws.entity.Solution;

import java.time.LocalDateTime;
import java.util.List;

public class SolutionService {
    private SolutionDao solutionDao;

    public SolutionService(SolutionDao solutionDao) {
        this.solutionDao = solutionDao;
    }

    public List<Solution> findAll() {
        List<Solution> solutions = solutionDao.findAll();
        System.out.println("Obtain solutions: " + solutions.size());
        return solutions;
    }

    public void add(Solution solution) {
        LocalDateTime now = LocalDateTime.now();
        solution.setPublishDate(now);
        solutionDao.add(solution);
        System.out.println("Solution added");
    }

    public List<Solution> filterByTask(String filter) {
        return solutionDao.filterByTask(filter);
    }
}
