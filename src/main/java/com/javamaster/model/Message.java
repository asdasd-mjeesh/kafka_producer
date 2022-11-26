package com.javamaster.model;

import java.util.Objects;

public class Message {
    private Long id;
    private String message;
    private MessageInfo messageInfo;

    public Message() {  }

    public Message(Long id, String message, MessageInfo messageInfo) {
        this.id = id;
        this.message = message;
        this.messageInfo = messageInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageInfo getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(MessageInfo messageInfo) {
        this.messageInfo = messageInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(message, message1.message) && Objects.equals(messageInfo, message1.messageInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, messageInfo);
    }

    @Override
    public String toString() {
        return "Message{" +
               "id=" + id +
               ", message='" + message + '\'' +
               ", messageInfo=" + messageInfo +
               '}';
    }
}
