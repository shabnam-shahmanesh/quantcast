package com.quantcast.assignment.exception;

/**
 * A <code>DateIsInvalidException</code> would be thrown
 * if the date value is not valid or in correct format
 *
 * @author Shabnam Shahmanesh
 */
public class InvalidDateException extends RuntimeException{
    private static final long serialVersionUID = 7718828512143293558L;

    public InvalidDateException() {
        super("Date format is not valid!");
    }
}
