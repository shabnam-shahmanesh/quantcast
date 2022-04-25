package com.quantcast.assignment.exception;

/**
 * A <code>LogFileNotFoundException</code> would be thrown
 * if the requested file does not exist
 *
 * @author Shabnam Shahmanesh
 */
public class InvalidLogFileException extends RuntimeException{
    private static final long serialVersionUID = 7718828512143293558L;

    public InvalidLogFileException() {
        super("The requested cookie log file format is not valid or file not found!");
    }
}
