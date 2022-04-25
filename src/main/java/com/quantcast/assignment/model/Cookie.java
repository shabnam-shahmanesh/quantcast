package com.quantcast.assignment.model;

import com.quantcast.assignment.exception.InvalidCookieException;
import com.quantcast.assignment.exception.InvalidDateException;
import com.quantcast.assignment.util.DateUtil;

import java.util.Objects;

/**
 * A <code>Cookie</code> this class contains the
 * occurrence date and also the cookie string
 *
 * @author Shabnam Shahmanesh
 */
public class Cookie {

    private final String date;
    private final String cookieString;

    /**
     * create new instance of Cookie object.
     *
     * @param date date.
     * @param cookie cookie.
     *
     * @throws InvalidDateException happen if date format is not valid
     * @throws InvalidCookieException happen if cookie is null or empty
     */
    public Cookie(String date, String cookie) {

        if (!Objects.isNull(date) && DateUtil.isValidDate(date)) {
            this.date = date;
        } else {
            throw new InvalidDateException();
        }
        if (Objects.isNull(cookie) || !cookie.trim().equals("")) {
            this.cookieString = cookie;
        } else {
            throw new InvalidCookieException();
        }

    }

    public String getDate() {
        return date;
    }

    public String getCookieString() {
        return cookieString;
    }

}
