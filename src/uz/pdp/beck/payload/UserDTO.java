package uz.pdp.beck.payload;

import uz.pdp.beck.model.User;

import java.util.UUID;

public record UserDTO(UUID id, String name, String username, String phoneNumber) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getUsername(), user.getPhoneNumber());
    }
}