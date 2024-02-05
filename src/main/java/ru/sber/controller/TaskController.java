package ru.sber.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sber.model.User;
import ru.sber.repository.TaskRepository;
import ru.sber.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @GetMapping
    public String show() {
        System.out.println("#Выводим список пользователей");
//        System.out.println(userRepository.getRandomUser());
        return "hello";
    }
}
