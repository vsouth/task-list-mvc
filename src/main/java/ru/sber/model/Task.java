package ru.sber.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @NonNull
    private int id;
    @NonNull
    @NotEmpty(message = "Название не должно быть пустым!")
    @Size(min = 5, max = 32, message = "Название должно быть от 5 до 32 символов!")
    private String title;
    private String description;
    @NonNull
    private Priority priority;
    @NonNull
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadlineAt;
    @NonNull
    private boolean isCompleted;
    @NonNull
    private User creator;
}
