package com.quantcast.assignment;

import com.quantcast.assignment.process.CookieLogProcessor;
import org.apache.commons.cli.*;

/**
 * A <code>Runner</code> accept the input parameter and execute CookieLogProcessor
 *
 * @author Shabnam Shahmanesh
 */
public class Runner {

    /**
     * execute the CookieLogProcessor and print the hottest cookie
     * @param args An array of input parameter
     */
    public static void main(String[] args) {
        Options options = new Options();

        try {
            options.addRequiredOption("f", "fileName", true, "Input the file name")
                    .addRequiredOption("d", "searchDate", true, "Input the search date");
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            System.out.println(CookieLogProcessor.retrieveHottestCookie(cmd.getOptionValue("fileName"), cmd.getOptionValue("searchDate")));
        } catch (ParseException pe) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Please, follow the instructions below:", options);
        }

    }


}
