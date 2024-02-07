package ru.sber.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class Task {
    @NonNull
    private int id;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private Priority priority;
    @NonNull
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime deadlineAt;
    @NonNull
    private boolean isCompleted;
    @NonNull
    private User creator;
}
