package uz.pdp.beck.payload;

import java.util.UUID;

public record ChatDTO(UUID id, UserDTO contact) {
}