package ru.sber.db;

import org.springframework.stereotype.Component;
import ru.sber.model.Priority;
import ru.sber.model.Task;
import ru.sber.model.User;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {
    public final List<Task> TASKS = new ArrayList<>();
    public final List<User> USERS = new ArrayList<>();

    public int LAST_USERS_ID = 0;
    public int LAST_TASKS_ID = 0;

    @PostConstruct
    void init() {
        USERS.add(new User(++LAST_USERS_ID, "qwe", "123"));
        USERS.add(new User(++LAST_USERS_ID, "qwerty", "123456"));

        TASKS.add(new Task(++LAST_TASKS_ID, "Покушать кашу", Priority.HIGH, LocalDateTime.of(2011, 10, 23, 15, 30), false, USERS.get(0)));
        TASKS.add(new Task(++LAST_TASKS_ID, "Дописать интеграцию с ФП САЗ", Priority.LOW, LocalDate.of(2024, 2, 10).atStartOfDay(), false, USERS.get(1)));
        TASKS.add(new Task(++LAST_TASKS_ID, "Вссеее", Priority.NO, LocalDate.of(2023, 10, 20).atStartOfDay(), true, USERS.get(1)));
        TASKS.add(new Task(++LAST_TASKS_ID, "Ну и еще одно дело", Priority.HIGH, LocalDateTime.of(2023, 7, 7, 10, 10), false, USERS.get(1)));
    }
}