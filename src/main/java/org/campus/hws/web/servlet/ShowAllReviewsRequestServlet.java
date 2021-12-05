package org.campus.hws.web.servlet;

import org.campus.hws.entity.Solution;
import org.campus.hws.service.SolutionService;
import org.campus.hws.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllReviewsRequestServlet extends HttpServlet {
    private SolutionService solutionService;

    public ShowAllReviewsRequestServlet(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Solution> solutions = getSolutions(req);
        displaySolutions(solutions, resp);
    }

    private List<Solution> getSolutions(HttpServletRequest req) {
        List<Solution> solutions;

        String filter = req.getParameter("task-filter");
        if (filter == null || filter.equals("All")) {
            solutions = solutionService.findAll();
        } else {
            solutions = solutionService.filterByTask(filter);
        }

        return solutions;
    }

    private void displaySolutions(List<Solution> solutions, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("solutions", solutions);

        String page = pageGenerator.getPage("reviews_list.html", parameters);
        resp.getWriter().write(page);
    }
}