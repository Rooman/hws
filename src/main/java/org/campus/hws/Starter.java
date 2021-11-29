package org.campus.hws;

import org.campus.hws.dao.jdbc.JdbcSolutionDao;
import org.campus.hws.entity.Solution;
import org.campus.hws.service.SolutionService;
import org.campus.hws.web.servlet.ShowAllReviewsRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        // config

        // dao
        JdbcSolutionDao jdbcSolutionDao = new JdbcSolutionDao();

        // service
        SolutionService solutionService = new SolutionService(jdbcSolutionDao);

        // servlet
        ShowAllReviewsRequestServlet showAllReviewsRequestServlet = new ShowAllReviewsRequestServlet(solutionService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(showAllReviewsRequestServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
