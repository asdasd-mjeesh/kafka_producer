package com.javamaster.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageInfo {
    private LocalDateTime dateOfSending;
    private User sender;
    private User recipient;

    public MessageInfo() {  }

    public MessageInfo(LocalDateTime dateOfSending, User sender, User recipient) {
        this.dateOfSending = dateOfSending;
        this.sender = sender;
        this.recipient = recipient;
    }

    public LocalDateTime getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(LocalDateTime dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageInfo that = (MessageInfo) o;
        return Objects.equals(dateOfSending, that.dateOfSending) && Objects.equals(sender, that.sender) && Objects.equals(recipient, that.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfSending, sender, recipient);
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
               "dateOfSending=" + dateOfSending +
               ", sender=" + sender +
               ", recipient=" + recipient +
               '}';
    }
}
