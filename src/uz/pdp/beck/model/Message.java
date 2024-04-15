package uz.pdp.beck.model;

import java.util.Date;
import java.util.UUID;

public class Message {
    private static int sequence = 1;
    private final int id;

    {
        this.id = sequence++;
    }

    private final Date date = new Date();
    private Chat chat;
    private UUID senderId; // or firstSide || secondSide
    private String body;

    public Message() {
    }

    public Message(Chat chat, UUID senderId, String body) {
        this.chat = chat;
        this.senderId = senderId;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
