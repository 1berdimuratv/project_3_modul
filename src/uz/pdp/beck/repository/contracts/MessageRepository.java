package uz.pdp.beck.repository.contracts;

import uz.pdp.beck.model.Message;
import uz.pdp.beck.model.User;

import java.util.List;
import java.util.UUID;

public interface MessageRepository {

    List<Message> findAllByChatId(UUID chatId);

    boolean save(Message message);
}
