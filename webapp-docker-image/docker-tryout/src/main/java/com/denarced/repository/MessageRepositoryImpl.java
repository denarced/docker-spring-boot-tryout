package com.denarced.repository;

import com.denarced.domain.Message;
import com.denarced.exception.MessageNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMessage(Message message) {
        session().persist(message);
    }

    @Override
    public Message getMessage(Long id) {
        return (Message) session().get(Message.class, id);
    }

    @Override
    public Collection<Message> getAllMessages() {
        return session().createCriteria(Message.class).list();
    }

    @Override
    public void deleteMessage(Long id) {
        session()
            .getNamedQuery("message.deleteById")
            .setLong("id", id)
            .executeUpdate();
    }

    @Override
    public Message editMessage(long id, Message message) throws MessageNotFoundException {
        Message originalMessage = (Message) session().get(Message.class, id);
        if (originalMessage == null) {
            throw new MessageNotFoundException(id);
        }
        return originalMessage.update(message);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
