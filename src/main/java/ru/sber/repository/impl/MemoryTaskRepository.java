package ru.sber.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.sber.db.Database;
import ru.sber.model.Task;
import ru.sber.repository.TaskRepository;
import ru.sber.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MemoryTaskRepository implements TaskRepository {
    private final UserRepository userRepository;
    private final Database database;

    @Override
    public List<Task> findAll() {
        return database.TASKS;
    }

    @Override
    public Optional<Task> findById(int id) {
        return database.TASKS.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }

    @Override
    public boolean delete(int id) {
        return database.TASKS.removeIf(item -> item.getId() == id);
    }

    @Override
    public void update(Task task) {
        database.TASKS.stream()
                .filter(item -> item.getId() == task.getId())
                .findFirst()
                .ifPresent((editTask) -> database.TASKS.set(editTask.getId(), task));
    }

    @Override
    public boolean create(Task task) {
        ++database.LAST_TASKS_ID;
        task.setId(database.LAST_TASKS_ID);
        task.setCreatedAt(LocalDateTime.now());
        task.setCreator(userRepository.getRandomUser());
        return database.TASKS.add(task);
    }
}