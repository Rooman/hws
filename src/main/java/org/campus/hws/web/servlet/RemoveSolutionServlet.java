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

public class RemoveSolutionServlet extends HttpServlet {
    private SolutionService solutionService;

    public RemoveSolutionServlet(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        solutionService.removeSolution(id);
        resp.sendRedirect("reviews_list.html");
    }
}
