package ru.sber.repository.impl;

import org.springframework.stereotype.Repository;
import ru.sber.model.User;
import ru.sber.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class MemoryUserRepository implements UserRepository {
    private static final List<User> USERS = new ArrayList<>();
    private static final Random RND = new Random();

    static {
        USERS.add(new User(1, "qwe", "123"));
        USERS.add(new User(2, "qwerty", "123456"));
    }

    @Override
    public User getRandomUser() {
        return USERS.get(RND.nextInt(USERS.size()));
    }
}
