package ru.sber.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.sber.model.Task;
import ru.sber.repository.TaskRepository;
import ru.sber.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MemoryTaskRepository implements TaskRepository {
    public static final List<Task> TASKS = new ArrayList<>();
    public static int LAST_TASKS_ID = 0;

    private final UserRepository userRepository;

    @Override
    public List<Task> findAll() {
        return TASKS;
    }

    @Override
    public Optional<Task> findById(int id) {
        return TASKS.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    @Override
    public boolean delete(int id) {
        return TASKS.removeIf(item -> item.getId() == id);
    }

    @Override
    public void update(Task task) {
        TASKS.stream()
                .filter(item -> item.getId() == task.getId())
                .findFirst()
                .ifPresent((editTask) -> TASKS.set(editTask.getId(), task));
    }

    @Override
    public boolean create(Task task) {
        ++LAST_TASKS_ID;
        task.setId(LAST_TASKS_ID);
        task.setCreatedAt(LocalDateTime.now());
        task.setCreator(userRepository.getRandomUser());
        return TASKS.add(task);
    }
}
