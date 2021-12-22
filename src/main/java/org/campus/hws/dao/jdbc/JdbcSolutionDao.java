package org.campus.hws.dao.jdbc;

import org.campus.hws.dao.SolutionDao;
import org.campus.hws.dao.jdbc.mapper.SolutionRowMapper;
import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSolutionDao implements SolutionDao {
    private static final SolutionRowMapper SOLUTION_ROW_MAPPER = new SolutionRowMapper();


    private static final String FIND_ALL_SQL = "SELECT id, github_link, author, comments, publish_date, task_name FROM Solution;";
    private static final String ADD_SQL = """
            INSERT INTO Solution (github_link, author, comments, publish_date, task_name) 
            VALUES(?, ?, ?, ?, ?);
            """;
    private static final String FIND_BY_TASK_SQL = """
            SELECT id, github_link, author, comments, publish_date, task_name 
            FROM Solution WHERE task_name=?;
            """;


    @Override
    public List<Solution> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Solution> solutions = new ArrayList<>();
            while (resultSet.next()) {
                Solution solution = SOLUTION_ROW_MAPPER.mapRow(resultSet);
                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // db unit
    // db rider
    @Override
    public void add(Solution solution) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL)) {
            preparedStatement.setString(1, solution.getGithubLink());
            preparedStatement.setString(2, solution.getAuthor());
            preparedStatement.setString(3, solution.getComment());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(solution.getPublishDate()));
            preparedStatement.setString(5, solution.getTaskType().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with solution insert", e);
        }
    }

    @Override
    public List<Solution> findByTaskName(TaskType taskType) {
        List<Solution> solutions = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TASK_SQL)) {
            preparedStatement.setString(1, taskType.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Solution solution = SOLUTION_ROW_MAPPER.mapRow(resultSet);
                    solutions.add(solution);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with find solution by task name", e);
        }

        
        return solutions;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hws",
                "user", "pswd");
    }
}
