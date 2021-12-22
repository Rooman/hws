package org.campus.hws.service;

import lombok.extern.slf4j.Slf4j;
import org.campus.hws.dao.SolutionDao;
import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;
import org.campus.hws.request.SolutionRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class SolutionService {
    private SolutionDao solutionDao;

    public SolutionService(SolutionDao solutionDao) {
        this.solutionDao = solutionDao;
    }

    public List<Solution> findAll(SolutionRequest solutionRequest) {
        List<Solution> solutions;

        if (solutionRequest.getTaskType()!= null){
            solutions = findByTaskName(solutionRequest.getTaskType());
        } else {
            solutions = solutionDao.findAll();
        }

        log.info("Obtain solutions: {}", solutions.size());
        return solutions;
    }

    public void add(Solution solution) {
        LocalDateTime now = LocalDateTime.now();
        solution.setPublishDate(now);
        solutionDao.add(solution);
        log.info("Solution added: \n\t{}", solution);
    }

    public List<Solution> findByTaskName(TaskType taskType) {
        List<Solution> solutions = solutionDao.findByTaskName(taskType);
        log.info("Obtain solutions by task '{}': {}", taskType, solutions.size());
        return solutions;
    }
}
