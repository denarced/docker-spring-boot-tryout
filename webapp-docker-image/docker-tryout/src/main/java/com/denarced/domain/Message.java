package com.denarced.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
        name = "message.deleteById",
        query = "DELETE from Message WHERE id=:id"
    )
})
@Entity
@Table(name = "t_message")
@SequenceGenerator(name = "t_message_id_seq", sequenceName = "t_message_id_seq")
public class Message {
    @Id
    @GeneratedValue(generator = "t_message_id_seq")
    private Long id;

    @Column(name = "c_message")
    private String message;

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message update(Message message) {
        setMessage(message.getMessage());
        return this;
    }
}
