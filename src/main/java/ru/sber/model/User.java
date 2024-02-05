package ru.sber.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    private final int id;
    @NonNull
    private String login;
    @NonNull
    private String password;
}
