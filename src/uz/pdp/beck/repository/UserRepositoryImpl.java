package uz.pdp.beck.repository;

import uz.pdp.beck.model.User;
import uz.pdp.beck.repository.contracts.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users  = new ArrayList<>();
    private static final UserRepository instance = new UserRepositoryImpl(); // eager
    private UserRepositoryImpl() {
    }
    public static UserRepository getInstance() {
        return instance;
    }

    @Override
    public boolean save(User user) {
        users.add(user);
        return true;
    }

    @Override
    public User findByUserName(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    @Override
    public User findByUserId(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }
}
