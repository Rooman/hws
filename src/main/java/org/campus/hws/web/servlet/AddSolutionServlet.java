//package org.campus.hws.web.servlet;
//
//import org.campus.hws.entity.Solution;
//import org.campus.hws.entity.TaskType;
//import org.campus.hws.service.SolutionService;
//import org.campus.hws.web.util.PageGenerator;
//
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AddSolutionServlet extends HttpServlet {
//    private SolutionService solutionService;
//
//    public AddSolutionServlet(SolutionService solutionService) {
//        this.solutionService = solutionService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        PageGenerator pageGenerator = PageGenerator.instance();
//        TaskType[] taskTypes = TaskType.values();
//        HashMap<String, Object> parameters = new HashMap<>();
//        parameters.put("taskTypes", taskTypes);
//
//        String page = pageGenerator.getPage("add_solution.html", parameters);
//        resp.getWriter().write(page);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        try {
//            Solution solution = getSolutionFromRequest(req);
//            solutionService.add(solution);
//            resp.sendRedirect("reviews_list.html");
//        } catch (Exception e) {
//            String errorMessage = "Your solution has not been added! Please, enter correct data in the fields";
//            PageGenerator pageGenerator = PageGenerator.instance();
//
//            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
//            String page = pageGenerator.getPage("add_solution.html", parameters);
//
//            resp.getWriter().write(page);
//        }
//    }
//
//
//    private Solution getSolutionFromRequest(HttpServletRequest req) {
//        String taskName = req.getParameter("task_name");
//        return Solution.builder()
//                .githubLink(req.getParameter("github_link"))
//                .comment(req.getParameter("comment"))
//                .taskType(TaskType.getById(taskName))
//                .author(req.getParameter("author"))
//                .build();
//    }
//}
