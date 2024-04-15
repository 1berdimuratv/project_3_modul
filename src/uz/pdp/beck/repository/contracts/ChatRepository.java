package uz.pdp.beck.repository.contracts;

import uz.pdp.beck.model.Chat;
import uz.pdp.beck.model.User;

import java.util.List;
import java.util.UUID;

public interface ChatRepository {
    List<Chat> findMyChats(UUID id);
    
    Chat findByFirstOrSecondSide(UUID id, UUID id1);

    boolean save(Chat chat);

    Chat findById(UUID uuid);
}
