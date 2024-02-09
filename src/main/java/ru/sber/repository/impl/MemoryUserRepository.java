package ru.sber.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.sber.db.Database;
import ru.sber.model.User;
import ru.sber.repository.UserRepository;

import java.util.Random;

@Repository
@RequiredArgsConstructor
public class MemoryUserRepository implements UserRepository {
    private static final Random RND = new Random();
    private final Database database;

    @Override
    public User getRandomUser() {
        return database.USERS.get(RND.nextInt(database.LAST_USERS_ID));
    }
}