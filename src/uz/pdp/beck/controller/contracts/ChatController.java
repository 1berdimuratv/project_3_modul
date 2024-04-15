package uz.pdp.beck.controller.contracts;

import uz.pdp.beck.payload.ChatDTO;

import java.util.List;
import java.util.UUID;

public interface ChatController {

    ChatDTO findOrCreateUserByUsername(String username, UUID id);
    List<ChatDTO> findMyChats(UUID currentId);
}
