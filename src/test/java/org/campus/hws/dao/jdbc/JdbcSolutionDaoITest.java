package org.campus.hws.dao.jdbc;

import org.campus.hws.entity.Solution;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void testRemoveAndAddSolutions(){
        JdbcSolutionDao jdbcSolutionDao = new JdbcSolutionDao();
        List<Solution> solutionsBefore = jdbcSolutionDao.findAll();
        int sizeBefore = solutionsBefore.size();
        LocalDateTime localDate = LocalDateTime.of(2021, 11, 11, 12, 22);
        Solution solution = Solution.builder().
                id(4)
                .githubLink("google.com")
                .comment("comment")
                .taskName("Linked list")
                .author("Author")
                .publishDate(localDate)
                .build();
        jdbcSolutionDao.addSolution(solution);
        List<Solution> solutions = jdbcSolutionDao.findAll();
        int size = solutions.size();

        jdbcSolutionDao.removeSolution(4);
        List<Solution> solutionsAfter = jdbcSolutionDao.findAll();
        int sizeAfter = solutionsAfter.size();

        assertEquals(sizeBefore, sizeAfter);
        assertEquals(size, sizeBefore+1);
    }
}