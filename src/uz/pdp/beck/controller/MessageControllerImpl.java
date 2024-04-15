package uz.pdp.beck.controller;

import uz.pdp.beck.controller.contracts.ChatController;
import uz.pdp.beck.controller.contracts.MessageController;
import uz.pdp.beck.model.Chat;
import uz.pdp.beck.model.Message;
import uz.pdp.beck.model.User;
import uz.pdp.beck.payload.ChatDTO;
import uz.pdp.beck.payload.MessageAddDTO;
import uz.pdp.beck.payload.MessageDTO;
import uz.pdp.beck.payload.UserDTO;
import uz.pdp.beck.repository.ChatRepositoryImpl;
import uz.pdp.beck.repository.MessageRepositoryImpl;
import uz.pdp.beck.repository.UserRepositoryImpl;
import uz.pdp.beck.repository.contracts.ChatRepository;
import uz.pdp.beck.repository.contracts.MessageRepository;
import uz.pdp.beck.repository.contracts.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class MessageControllerImpl implements MessageController {

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final ChatRepository chatRepository = ChatRepositoryImpl.getInstance();
    private final MessageRepository messageRepository = MessageRepositoryImpl.getInstance();
    private static final MessageController instance = new MessageControllerImpl(); // eager
    private MessageControllerImpl() {
    }
    public static MessageController getInstance() {
        return instance;
    }


    @Override
    public List<MessageDTO> findAllMessagesByChatId(UUID chatId) {

        List<Message> messages = messageRepository.findAllByChatId(chatId);

        messages.sort(Comparator.comparing(Message::getDate));

        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages)
            messageDTOS.add(new MessageDTO(message));

        return messageDTOS;
    }

    @Override
    public boolean add(MessageAddDTO dto) {

        Chat chat = chatRepository.findById(dto.chatId());
        if (chat == null)
            throw new RuntimeException("Chat not found");

        return messageRepository.save(new Message(chat, dto.currentUserId(), dto.body()));
    }
}
