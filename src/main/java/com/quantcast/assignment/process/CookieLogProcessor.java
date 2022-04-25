package com.quantcast.assignment.process;

import com.quantcast.assignment.exception.CookieNotFoundException;
import com.quantcast.assignment.exception.InvalidLogFileException;
import com.quantcast.assignment.model.Cookie;
import com.quantcast.assignment.util.DateUtil;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A <code>CookieLogProcessor</code> retrieve the most active cookie
 * from a file in a file system. The file should be in csv format
 * each line contains [COOKIE],[TIMESTAMP]
 * and the first line assumes as header and won't be processed
 *
 * @author Shabnam Shahmanesh
 */
public class CookieLogProcessor {

    private CookieLogProcessor() {

    }

    /**
     * Retrieve the hottest cookie in the specified date
     * and return it or the as string.
     *
     * @param filePath   A string containing
     *                   the path of cookie log file.
     * @param searchDate A string containing
     *                   the indicates the search date.
     * @return String.
     * @throws InvalidLogFileException happens if log file is empty or only have header
     * @throws CookieNotFoundException happens if there is no cookie for search date
     */
    public static String retrieveHottestCookie(String filePath, String searchDate) {
        DateUtil.isValidDate(searchDate);
        List<Cookie> cookieList = readInputFile(filePath);
        if (Objects.isNull(cookieList) || cookieList.isEmpty()) {
            throw new InvalidLogFileException();
        }
        Map<String, Long> cookieCountMap = cookieList.parallelStream().filter(cookie -> cookie.getDate().equals(searchDate))
                .collect(Collectors.groupingBy(Cookie::getCookieString, Collectors.counting()));

        if (cookieCountMap.size() == 0) {
            throw new CookieNotFoundException();
        }
        Long maxActive = Collections.max(cookieCountMap.values());

        StringBuilder builder = new StringBuilder();

        cookieCountMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxActive))
                .forEach(e -> builder.append(e.getKey()).append("\n"));

        return builder.toString().trim();
    }

    /**
     * Read the cookie  log file and convert it to List of cookies
     * and return the converted list.
     * The first line of file will be skipped because
     * it assumes as header
     *
     * @param filePath A string containing
     *                 the path of cookie log file.
     * @return String.
     * @throws InvalidLogFileException happens if the requested file not find or invalid
     */
    private static List<Cookie> readInputFile(String filePath) {
        List<Cookie> inputList;
        File file = new File(filePath);

        try (
                InputStream inputStream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            Optional<String> firstLine = br.lines().findFirst();
            if (!firstLine.isPresent() || !firstLine.get().trim().equalsIgnoreCase("cookie,timestamp")) {
                throw new InvalidLogFileException();
            }

            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
        } catch (IOException e) {
            throw new InvalidLogFileException();
        }
        return inputList;
    }

    /**
     * Map each line of file to Cookie object.
     */
    private static final Function<String, Cookie> mapToItem = line -> {
        String[] p = line.split(",");
        if (p.length != 2) {
            throw new InvalidLogFileException();
        }
        return new Cookie(p[1].substring(0, 10), p[0]);
    };
}
