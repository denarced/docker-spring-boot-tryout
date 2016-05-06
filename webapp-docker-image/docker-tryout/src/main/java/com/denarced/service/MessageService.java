package com.denarced.service;

import com.denarced.domain.Message;
import com.denarced.exception.EmptyMessageException;
import com.denarced.exception.MessageNotFoundException;

import java.util.Collection;

public interface MessageService {
    Message addMessage(String message) throws EmptyMessageException;
    Message getMessage(Long id);
    Collection<Message> getAllMessages();
    void deleteMessage(Long id);
    Message editMessage(long id, Message message) throws MessageNotFoundException, EmptyMessageException;
}
