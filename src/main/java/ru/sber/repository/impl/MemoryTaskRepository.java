package ru.sber.repository.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.sber.db.Database;
import ru.sber.model.Task;
import ru.sber.model.User;
import ru.sber.repository.TaskRepository;
import ru.sber.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public boolean create(Task task, int userId) {
        ++database.LAST_TASKS_ID;
        task.setId(database.LAST_TASKS_ID);
        task.setCreatedAt(LocalDateTime.now());
        Optional<User> user = userRepository.getUserById(userId);
        if (user.isPresent()) {
            task.setCreator(user.get());
            return database.TASKS.add(task);
        }
        return false;
    }

    public List<Task> findByUserId(int userId) {
        return database.TASKS.stream()
                .filter(item -> item.getCreator().getId() == userId)
                .collect(Collectors.toList());
    }
}