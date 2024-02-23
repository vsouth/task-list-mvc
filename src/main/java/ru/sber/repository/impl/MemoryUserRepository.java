package ru.sber.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.sber.db.Database;
import ru.sber.model.User;
import ru.sber.repository.UserRepository;

import java.util.Optional;
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
    @Override
    public boolean createUser(User user) {
        ++database.LAST_USERS_ID;
        user.setId(database.LAST_USERS_ID);
        System.out.println(database.LAST_USERS_ID);
        return database.USERS.add(user);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return database.USERS.stream()
                .filter(o -> o.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(database.USERS.get(id-1));
    }
}