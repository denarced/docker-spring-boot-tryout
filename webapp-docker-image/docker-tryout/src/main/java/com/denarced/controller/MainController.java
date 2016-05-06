package com.denarced.controller;

import com.denarced.domain.Message;
import com.denarced.exception.EmptyMessageException;
import com.denarced.exception.MessageNotFoundException;
import com.denarced.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

@Controller
public class MainController {
    @Value("${nodeid}")
    private String nodeIdentifier;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public @ResponseBody String main() {
        return "well hello hello: " + deriveNodeIdentifier();
    }

    private String deriveNodeIdentifier() {
        return nodeIdentifier == null || nodeIdentifier.isEmpty()
            ? "empty ID"
            : nodeIdentifier;
    }

    @ExceptionHandler(EmptyMessageException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Message cannot be empty.")
    public void handleEmptyMessageException() {
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<String> handleMessageNotFoundException(final MessageNotFoundException e) {
        return new ResponseEntity<>("No such message with ID: " + e.getMessageId(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/message", method = {RequestMethod.POST})
    public @ResponseBody Message addMessage(@RequestBody Message message) throws EmptyMessageException {
        if (message.getMessage().trim().isEmpty()) {
            throw new EmptyMessageException();
        }
        return messageService.addMessage(message.getMessage().trim());
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public @ResponseBody Message getMessage(@PathVariable(value = "id") Long id) {
        return messageService.getMessage(id);
    }

    // Due to void return type, HTTP code 200 has to be defined or otherwise
    // the client gets HTTP 405: Request method 'DELETE' not supported.
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable(value = "id") Long id) {
        messageService.deleteMessage(id);
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.POST)
    public @ResponseBody Message editMessage(
            @PathVariable(value = "id") long id,
            @RequestBody Message message) throws EmptyMessageException, MessageNotFoundException {

        return messageService.editMessage(id, message);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public @ResponseBody Collection<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
