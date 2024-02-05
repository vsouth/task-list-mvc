package ru.sber.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class Task {
    private final int id;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private Priority priority;
    private final LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime deadlineAt;
    @NonNull
    private boolean isCompleted;
}
