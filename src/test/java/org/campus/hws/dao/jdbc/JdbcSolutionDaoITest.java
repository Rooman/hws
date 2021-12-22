package org.campus.hws.dao.jdbc;

import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSolutionDaoITest {
    @Test
    public void testFindAllReturnCorrectData() {
        JdbcSolutionDao jdbcSolutionDao = new JdbcSolutionDao();
        List<Solution> solutions = jdbcSolutionDao.findAll();

        assertFalse(solutions.isEmpty());
        for (Solution solution : solutions) {
            assertNotEquals(0, solution.getId());
            assertNotNull(solution.getAuthor());
            assertNotNull(solution.getComment());
            assertNotNull(solution.getPublishDate());
            assertNotNull(solution.getGithubLink());
        }
    }

    @Test
    public void testGetAllByTaskNameReturnsCorrectData() {
        JdbcSolutionDao jdbcSolutionDao = new JdbcSolutionDao();
        List<Solution> solutions = jdbcSolutionDao.findByTaskName(TaskType.LINKED_LIST);

        assertFalse(solutions.isEmpty());
        for (Solution solution : solutions) {
            assertNotEquals(0, solution.getId());
            assertNotNull(solution.getAuthor());
            assertNotNull(solution.getComment());
            assertNotNull(solution.getPublishDate());
            assertNotNull(solution.getGithubLink());
        }
    }
}