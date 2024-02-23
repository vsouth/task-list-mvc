package ru.sber.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.sber.model.Priority;
import ru.sber.model.Task;
import ru.sber.repository.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping
    public ModelAndView showAll(ModelAndView model) {
        List<Task> tasks = taskRepository.findAll();
        model.addObject("tasks", tasks);
        model.setViewName("list");
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
        return "redirect:/tasks";
    }
}
