package org.campus.hws.dao.jdbc;

import org.campus.hws.dao.SolutionDao;
import org.campus.hws.dao.jdbc.mapper.SolutionRowMapper;
import org.campus.hws.entity.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JdbcSolutionDao implements SolutionDao {
    private static final SolutionRowMapper SOLUTION_ROW_MAPPER = new SolutionRowMapper();

    private static final String FIND_ALL_SQL = """
            SELECT id, github_link, author, comments, publish_date, task_name
            FROM Solution;
            """;
    private static final String FILTER_BY_TASK_NAME_SQL = """
            SELECT id, github_link, author, comments, publish_date, task_name
            FROM Solution
            WHERE task_name = ?;
            """;
    private static final String ADD_SQL = """
            INSERT INTO Solution (github_link, author, comments, publish_date, task_name)
            VALUES(?, ?, ?, ?, ?);
            """;

    @Override
    public List<Solution> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            return findSolutions(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with finding all solutions", e);
        }
    }

    @Override
    public void add(Solution solution) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL)) {

            preparedStatement.setString(1, solution.getGithubLink());
            preparedStatement.setString(2, solution.getAuthor());
            preparedStatement.setString(3, solution.getComment());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(solution.getPublishDate()));
            preparedStatement.setString(5, solution.getTaskName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with solution insert", e);
        }
    }

    @Override
    public List<Solution> filterByTask(String filter) {
        return findByFilter(FILTER_BY_TASK_NAME_SQL, filter);
    }

    private List<Solution> findByFilter(String query, String filter) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, filter);
            return findSolutions(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with filtering solutions", e);
        }
    }

    private List<Solution> findSolutions(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Solution> solutions = new ArrayList<>();
            while (resultSet.next()) {
                Solution solution = SOLUTION_ROW_MAPPER.mapRow(resultSet);
                solutions.add(solution);
            }
            return solutions;
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hws",
                "user", "pswd");
    }
}
