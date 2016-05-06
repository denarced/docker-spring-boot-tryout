package com.denarced.exception;

public class MessageNotFoundException extends DockerTryoutException {
    private final long messageId;

    public MessageNotFoundException(long messageId) {
        this.messageId = messageId;
    }

    public long getMessageId() {
        return messageId;
    }
}
