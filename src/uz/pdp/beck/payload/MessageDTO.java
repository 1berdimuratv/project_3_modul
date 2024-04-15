package uz.pdp.beck.payload;

import uz.pdp.beck.model.Chat;
import uz.pdp.beck.model.Message;

import java.util.Date;
import java.util.UUID;

public record MessageDTO(int id, Date date , UUID senderId, String body) {

    public MessageDTO(Message msg) {
        this(msg.getId(), msg.getDate(), msg.getSenderId(), msg.getBody());
    }
}