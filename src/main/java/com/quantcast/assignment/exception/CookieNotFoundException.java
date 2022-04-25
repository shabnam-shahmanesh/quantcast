package com.quantcast.assignment.exception;

/**
 * A <code>CookieIsNotValid</code> would be thrown
 * if no cookie found
 *
 * @author Shabnam Shahmanesh
 */
public class CookieNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7718828512143293558L;

    public CookieNotFoundException() {
        super("No cookie found for specified date!");
    }
}
