package com.quantcast.assignment.exception;

/**
 * A <code>CookieIsNotValid</code> would be thrown
 * if the cookie value is not null or empty string
 *
 * @author Shabnam Shahmanesh
 */
public class InvalidCookieException extends RuntimeException{
    private static final long serialVersionUID = 7718828512143293558L;

    public InvalidCookieException() {
        super("Cookie is not valid!");
    }
}
