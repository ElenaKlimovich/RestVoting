package ru.rest.voting.util.exception;

public class VotingException extends RuntimeException {

    public VotingException() {
        super("Can't change vote after 11-00");
    }
}
