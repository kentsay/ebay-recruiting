package de.ebay.recruiting.csv;

import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Application {
    public static void main(String[] args) {

        final String dataDirectory = System.getProperty("user.dir") + "/src/testdata/";
        String adBookFileName = "address-book.csv";
        String libFileName = "library.csv";

        Options options = new Options();
        options.addOption("h", false, "-- display this message");
        options.addOption("s", true,  "-- set scale factor (SF) (default: 1)");
        options.addOption("t", true,  "-- set number of tenants (default: 1)");
        options.addOption("m", true,  "-- set distribution mode (uniform, zipf");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Main", options);
                System.exit(0);
            }
            if (cmd.hasOption("s")) {

            }

            //read file into stream, try-with-resources
            ArrayList<AddressBook> adList = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get(dataDirectory + adBookFileName))) {
                stream.forEach((line) -> {
                    String[] tokens = line.split(", ");
                    AddressBook addressBook = new AddressBook(tokens[0], tokens[1], tokens[2], tokens[3]);
                    adList.add(addressBook);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<LibraryData> libList = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get(dataDirectory + libFileName))) {
                stream.forEach((line) -> {
                    String[] tokens = line.split(", ");
                    LibraryData libData = new LibraryData(tokens[0], tokens[1]);
                    libList.add(libData);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
