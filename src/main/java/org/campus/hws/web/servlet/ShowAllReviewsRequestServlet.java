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
        List<Solution> solutions;

        if(req.getParameter("task") != null) {
            solutions = solutionService.findByTaskName(req.getParameter("task"));
        } else {
            solutions = solutionService.findAll();
        }

        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("solutions", solutions);

        String page = pageGenerator.getPage("reviews_list.html", parameters);
        resp.getWriter().write(page);
    }
}
