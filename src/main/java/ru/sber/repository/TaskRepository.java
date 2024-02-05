package ru.sber.repository;

import org.springframework.stereotype.Repository;
import ru.sber.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> findById(int id);

    boolean delete(int id);

    void update(Task task);

    boolean create(Task task);
}
