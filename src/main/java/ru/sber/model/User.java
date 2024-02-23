package ru.sber.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Scope("session")
public class User {
    @NonNull
    private int id;
    @NonNull
    private String login;
    @NonNull
    private String password;
}
