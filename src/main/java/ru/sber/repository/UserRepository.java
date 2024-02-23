package ru.sber.repository;

import ru.sber.model.User;

import java.util.Optional;

public interface UserRepository {
    User getRandomUser();
    boolean createUser(User user);
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(int id);
}
