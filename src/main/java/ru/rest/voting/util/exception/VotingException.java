package ru.rest.voting.util.exception;

import java.time.LocalTime;

public class VotingException extends RuntimeException {

    public VotingException(LocalTime deadline) {
        super("Can't change vote after " + deadline.toString());
    }
}
