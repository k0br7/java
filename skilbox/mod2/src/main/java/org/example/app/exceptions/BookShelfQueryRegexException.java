package org.example.app.exceptions;

public class BookShelfQueryRegexException extends Exception{
    private final String message;

    public BookShelfQueryRegexException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
