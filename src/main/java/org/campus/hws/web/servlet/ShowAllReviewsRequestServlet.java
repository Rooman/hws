package org.campus.hws.web.servlet;

import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;
import org.campus.hws.request.SolutionRequest;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Solution> solutions;
        SolutionRequest solutionRequest = new SolutionRequest();
        String taskTypeString = req.getParameter("task");
        TaskType[] taskTypes = TaskType.values();
        if (taskTypeString != null) {
            TaskType taskType = TaskType.getById(taskTypeString);
            solutionRequest.setTaskType(taskType);
        }
        solutions = solutionService.findAll(solutionRequest);


        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("solutions", solutions);
        parameters.put("taskTypes", taskTypes);

        String page = pageGenerator.getPage("reviews_list.html", parameters);
        resp.getWriter().write(page);
    }
}
