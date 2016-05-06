package com.denarced.service;

import com.denarced.domain.Message;
import com.denarced.exception.EmptyMessageException;
import com.denarced.exception.MessageNotFoundException;
import com.denarced.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public Message addMessage(String message) throws EmptyMessageException {
        if (message == null || message.trim().isEmpty()) {
            throw new EmptyMessageException();
        }
        Message toPersist = new Message().setMessage(message);
        messageRepository.addMessage(toPersist);
        return toPersist;
    }

    @Override
    @Transactional(readOnly = true)
    public Message getMessage(Long id) {
        return messageRepository.getMessage(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    @Override
    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteMessage(id);
    }

    @Override
    @Transactional(rollbackFor = {MessageNotFoundException.class})
    public Message editMessage(long id, Message message) throws MessageNotFoundException, EmptyMessageException {
        if (message.getMessage().trim().isEmpty()) {
            throw new EmptyMessageException();
        }
        return messageRepository.editMessage(id, message);
    }
}
