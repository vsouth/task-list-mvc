package ru.sber.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.model.Priority;
import ru.sber.model.Task;
import ru.sber.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping("/all")
    public ModelAndView showAll(ModelAndView model) {
        List<Task> tasks = taskRepository.findAll();
        model.addObject("tasks", tasks);
        model.setViewName("all_tasks");
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView showUserTasks(@PathVariable("id") Integer userId, ModelAndView model) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        model.addObject("tasks", tasks);
        model.setViewName("all_tasks");
        return model;
    }

    @GetMapping("/my")
    public ModelAndView showUserTasks(ModelAndView model, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("user-id");
        log.info(""+userId);
        if (userId == null) {
            model.setViewName("redirect:/index");
            return model;
        }
        List<Task> tasks = taskRepository.findByUserId(userId);
        model.addObject("tasks", tasks);
        model.setViewName("user_tasks");
        return model;
    }

    @GetMapping("/create")
    public ModelAndView showCreate(ModelAndView modelAndView) {
        modelAndView.setViewName("create");
        Task task = new Task();
        modelAndView.addObject(task);
        modelAndView.addObject("priorities", Priority.values());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute @Valid Task task, BindingResult bindingResult, HttpServletRequest request) {
        log.info("#task " + task);
        if (bindingResult.hasErrors()) {
            return "create";
        }

        taskRepository.create(task, (int) request.getSession().getAttribute("user-id"));
        return "redirect:/tasks/my";
    }
}
