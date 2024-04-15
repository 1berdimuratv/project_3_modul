package uz.pdp.beck.payload;

import uz.pdp.beck.model.Message;

import java.util.Date;
import java.util.UUID;

public record MessageAddDTO(String body, UUID chatId, UUID currentUserId) {

}