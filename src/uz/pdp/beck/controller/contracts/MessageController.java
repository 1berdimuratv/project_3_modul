package uz.pdp.beck.controller.contracts;

import uz.pdp.beck.payload.ChatDTO;
import uz.pdp.beck.payload.MessageAddDTO;
import uz.pdp.beck.payload.MessageDTO;

import java.util.List;
import java.util.UUID;

public interface MessageController {

    List<MessageDTO> findAllMessagesByChatId(UUID chatId);

    boolean add(MessageAddDTO messageAddDTO);
}
