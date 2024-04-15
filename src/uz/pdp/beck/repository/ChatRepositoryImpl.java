package uz.pdp.beck.repository;

import uz.pdp.beck.model.Chat;
import uz.pdp.beck.model.User;
import uz.pdp.beck.repository.contracts.ChatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatRepositoryImpl implements ChatRepository {
    private final List<Chat> chats  = new ArrayList<>();
    private static final ChatRepository instance = new ChatRepositoryImpl(); // eager
    private ChatRepositoryImpl() {
    }
    public static ChatRepository getInstance() {
        return instance;
    }

    @Override
    public List<Chat> findMyChats(UUID id) {
        List<Chat> res = new ArrayList<>();
        for (Chat chat : chats) {
            if (chat.getFirstSide().getId().equals(id) ||
                chat.getSecondSide().getId().equals(id)){
                res.add(chat);
            }
        }return res;
    }

    @Override
    public Chat findByFirstOrSecondSide(UUID firstSideId, UUID secondSideId) {
        for (Chat chat : chats) {
            User firstSide = chat.getFirstSide();
            User secondSide = chat.getSecondSide();
            if (
                    firstSide.getId().equals(firstSideId) && secondSide.getId().equals(secondSideId)
                            ||  firstSide.getId().equals(secondSideId) && secondSide.getId().equals(firstSideId)
            ) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public boolean save(Chat chat) {
        chats.add(chat);
        return true;
    }

    @Override
    public Chat findById(UUID uuid) {
        for (Chat chat : chats) {
            if (chat.getId().equals(uuid)) {
                return chat;
            }
        }
        return null;
    }
}
