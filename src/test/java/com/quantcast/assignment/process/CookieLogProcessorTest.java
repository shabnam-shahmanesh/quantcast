package com.quantcast.assignment.process;

import com.quantcast.assignment.exception.CookieNotFoundException;
import com.quantcast.assignment.exception.InvalidCookieException;
import com.quantcast.assignment.exception.InvalidDateException;
import com.quantcast.assignment.exception.InvalidLogFileException;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * A <code>CookieLogProcessorTest</code> test the CookieLogProcessorTest
 *
 * @author Shabnam Shahmanesh
 */
public class CookieLogProcessorTest {

    private static final String testFilesAbsolutePath;

    static {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        testFilesAbsolutePath = resourceDirectory.toFile().getAbsolutePath() + "\\";
    }

    /**
     * test if the cookie log file does not exist
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfFileNotFound() {
        assertThrows(InvalidLogFileException.class, () ->
                CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfFileNotFound.csv", "2018-09-02"));
    }

    /**
     * test if the cookie log file exist but is not valid
     */
    @Test
    public void retrieveHottestCookie_ThrownException_IfTheFileIsInvalid() {
        assertThrows(InvalidLogFileException.class, () ->
                CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrownException_IfTheFileIsInvalid.csv", "2018-09-02"));
    }

    /**
     * test if at least on date is invalid in cookie log file
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfOneDateIsInvalid() {
        assertThrows(InvalidDateException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfOneDateIsInvalid.csv", "2018-09-02"));
    }

    /**
     * test if at least on cookie is null or empty in cookie log file
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfOneCookieIsInvalid() {
        assertThrows(InvalidCookieException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfOneCookieIsInvalid.csv", "2018-09-02"));
    }

    /**
     * test if the cookie log file has only one hot cookie for specified date
     */
    @Test
    public void retrieveHottestCookie_Successful_IfOneActiveCookieExists() {
        String resultCookie = CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_Successful_IfOneActiveCookieExists.csv", "2018-12-08");
        assertEquals("4sMM2LxV07bPJzwf", resultCookie);
    }

    /**
     * test if the cookie log file has two hot cookie for specified date
     */
    @Test
    public void retrieveHottestCookie_Successful_IfTwoActiveCookieExists() {
        String resultCookie = CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_Successful_IfTwoActiveCookieExists.csv", "2018-12-09");
        assertTrue(resultCookie.contains("5UAVanZf6UtGyKVT"));
        assertTrue(resultCookie.contains("AtY0laUfhglK3lC7"));
    }

    /**
     * test if the specified date is not valid
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfSearchDateIsNotValid() {
        assertThrows(InvalidDateException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_Successful_IfTwoActiveCookieExists.csv", "2018-12-44"));
    }

    /**
     * test if the cookie log file has only one line for specified date
     */
    @Test
    public void retrieveHottestCookie_Successful_IfThereIsOnlyOneCookie() {
        String resultCookie = CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_Successful_IfThereIsOnlyOneCookie.csv", "2018-12-09");
        assertTrue(resultCookie.contains("AtY0laUfhglK3lC7"));
    }

    /**
     * test if the cookie log file has only no line for specified date
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfThereIsNoCookie() {
        assertThrows(CookieNotFoundException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfThereIsNoCookie.csv", "2018-12-06"));
    }

    /**
     * test if the cookie log file is empty
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfTheFileIsEmpty() {
        assertThrows(InvalidLogFileException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfTheFileIsEmpty.csv", "2018-12-09"));
    }

    /**
     * test if the cookie log file has only header line
     */
    @Test
    public void retrieveHottestCookie_ThrowsException_IfTheFileHasOnlyHeader() {
        assertThrows(InvalidLogFileException.class, () -> CookieLogProcessor.retrieveHottestCookie(testFilesAbsolutePath + "retrieveHottestCookie_ThrowsException_IfTheFileHasOnlyHeader.csv", "2018-12-09"));
    }
}
