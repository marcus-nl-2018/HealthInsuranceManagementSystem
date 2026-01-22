package com.health.insurance.exception;

public class NavigationException extends RuntimeException {

    public NavigationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NavigationException(String message) {
        super(message);
    }
}
