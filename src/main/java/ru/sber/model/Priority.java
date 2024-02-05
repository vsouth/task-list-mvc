package ru.sber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    NO("Нет"), LOW("Низкий"), HIGH("Высокий");

    private final String value;
}
