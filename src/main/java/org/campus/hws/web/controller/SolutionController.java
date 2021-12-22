package org.campus.hws.web.controller;

import org.campus.hws.entity.Solution;
import org.campus.hws.entity.TaskType;
import org.campus.hws.request.SolutionRequest;
import org.campus.hws.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller // (solutionController)
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @RequestMapping(path = "/solutions", method = RequestMethod.GET)
    protected String findAll(@RequestParam(value = "task", required = false) String taskTypeString
            , Model model) {
        System.out.println("In solutions controller");
        SolutionRequest solutionRequest = new SolutionRequest();

        TaskType[] taskTypes = TaskType.values();
        if (taskTypeString != null) {
            TaskType taskType = TaskType.getById(taskTypeString);
            solutionRequest.setTaskType(taskType);
        }

        List<Solution> solutions = solutionService.findAll(solutionRequest);

        model.addAttribute("solutions", solutions);
        model.addAttribute("taskTypes", taskTypes);

        return "reviews_list";
    }

    @RequestMapping(path = "/solutions/add", method = RequestMethod.GET)
    protected String getAddSolutionPage(Model model) {
        TaskType[] taskTypes = TaskType.values();
        model.addAttribute("taskTypes", taskTypes);

        return "add_solution";
    }

    @RequestMapping(path = "/solutions/add", method = RequestMethod.POST)
    protected String addSolution(HttpServletRequest req) {

        String taskName = req.getParameter("task_name");
        Solution solution = Solution.builder()
                .githubLink(req.getParameter("github_link"))
                .comment(req.getParameter("comment"))
                .taskType(TaskType.getById(taskName))
                .author(req.getParameter("author"))
                .build();


        solutionService.add(solution);
        return "redirect:/solutions";
    }


    public void setSolutionService(SolutionService solutionService) {
        this.solutionService = solutionService;
    }
}
