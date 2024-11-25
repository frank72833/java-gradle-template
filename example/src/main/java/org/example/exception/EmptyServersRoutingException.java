package org.example.exception;

public class EmptyServersRoutingException extends RuntimeException {
    public EmptyServersRoutingException() {
        super("Empty servers list. Request cannot be routed");
    }
}
