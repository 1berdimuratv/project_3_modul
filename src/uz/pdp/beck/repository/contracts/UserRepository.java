package uz.pdp.beck.repository.contracts;

import uz.pdp.beck.model.User;

import java.util.UUID;

public interface UserRepository {

    boolean save(User user);

    User findByUserName(String username);

    User findByUserId(UUID id);
}
