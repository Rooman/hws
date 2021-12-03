package org.campus.hws.service;

import org.campus.hws.dao.SolutionDao;
import org.campus.hws.entity.Solution;

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

    public void addSolution(Solution solution) {
        solutionDao.addSolution(solution);
        System.out.println("Solution added");
    }

    public void removeSolution(int id) {
        solutionDao.removeSolution(id);
        System.out.println("Solution with id " + id + "was removed");
    }


}
