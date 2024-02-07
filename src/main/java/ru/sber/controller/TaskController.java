package ru.sber.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.model.Task;
import ru.sber.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping
    public String show(Model model) {
        List<Task> tasks = taskRepository.findAll();
//        log.info("#Выводим список задач");
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model) {
        Optional<Task> mbTask = taskRepository.findById(id);
//        log.info("Показавыем задачу с id = " + id);
//        log.info("Пытаемся вытащить атрибут" + model.getAttribute("tasks"));
        mbTask.ifPresent(task -> {
            model.addAttribute("task", task);
        });
        return "show";
    }
}
