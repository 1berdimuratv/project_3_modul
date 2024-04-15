package uz.pdp.beck.repository;

import uz.pdp.beck.model.Message;
import uz.pdp.beck.model.User;
import uz.pdp.beck.repository.contracts.MessageRepository;
import uz.pdp.beck.repository.contracts.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageRepositoryImpl implements MessageRepository {
    private final List<Message> messages  = new ArrayList<>();
    private static final MessageRepository instance = new MessageRepositoryImpl(); // eager
    private MessageRepositoryImpl() {
    }
    public static MessageRepository getInstance() {
        return instance;
    }


    @Override
    public List<Message> findAllByChatId(UUID chatId) {
        ArrayList<Message> res = new ArrayList<>();
        for (Message message : messages) {
            if (message.getChat().getId().equals(chatId)) {
                res.add(message);
            }
        }
        return res;
    }

    @Override
    public boolean save(Message message) {
        messages.add(message);
        return true;
    }
}
