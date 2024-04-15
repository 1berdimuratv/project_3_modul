package uz.pdp.beck.controller;

import uz.pdp.beck.controller.contracts.ChatController;
import uz.pdp.beck.model.Chat;
import uz.pdp.beck.model.User;
import uz.pdp.beck.payload.ChatDTO;
import uz.pdp.beck.payload.UserDTO;
import uz.pdp.beck.repository.ChatRepositoryImpl;
import uz.pdp.beck.repository.contracts.ChatRepository;
import uz.pdp.beck.repository.contracts.UserRepository;
import uz.pdp.beck.repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatControllerImpl implements ChatController {

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final ChatRepository chatRepository = ChatRepositoryImpl.getInstance();
    private static final ChatController instance = new ChatControllerImpl(); // eager
    private ChatControllerImpl() {
    }
    public static ChatController getInstance() {
        return instance;
    }

    @Override
    public List<ChatDTO> findMyChats(UUID currentId) {
        List<Chat> myChats = chatRepository.findMyChats(currentId);
        List<ChatDTO> res = new ArrayList<>();
        for (Chat myChat : myChats) {
            if (myChat.getFirstSide().getId().equals(currentId)) {
                res.add(new ChatDTO(myChat.getId(),new UserDTO(myChat.getSecondSide())));
            }else {
                res.add(new ChatDTO(myChat.getId(),new UserDTO(myChat.getFirstSide())));
            }
        }
        return res;
    }

    @Override
    public ChatDTO findOrCreateUserByUsername(String username, UUID id) {
        User contact = userRepository.findByUserName(username);
        if (contact == null)
            throw new RuntimeException("User not found");

        User currentUser = userRepository.findByUserId(id);
        if (currentUser == null)
            throw new RuntimeException("User not authenticated");

        Chat chat = chatRepository.findByFirstOrSecondSide(
                currentUser.getId(),
                contact.getId()
        );

        if (chat == null) {
            chat = new Chat(currentUser, contact);
            chatRepository.save(chat);
        }

        return new ChatDTO(chat.getId(), new UserDTO(contact));
    }
}
