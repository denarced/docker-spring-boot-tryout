package com.denarced.repository;

import com.denarced.domain.Message;
import com.denarced.exception.MessageNotFoundException;

import java.util.Collection;

public interface MessageRepository {
    void addMessage(Message message);
    Message getMessage(Long id);
    Collection<Message> getAllMessages();
    void deleteMessage(Long id);
    Message editMessage(long id, Message message) throws MessageNotFoundException;
}
