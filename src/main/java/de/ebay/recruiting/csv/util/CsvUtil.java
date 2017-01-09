package de.ebay.recruiting.csv.util;


import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class CsvUtil {

    final static String dataDirectory = System.getProperty("user.dir") + "/src/testdata/";

    public static HashMap<String, AddressBook> parseAddressBookCsv(String filename) {

        HashMap<String, AddressBook> adMap = new HashMap<>();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(dataDirectory + filename))) {
            stream.forEach((line) -> {
                String[] tokens = line.split(", ");
                AddressBook addressBook = new AddressBook(tokens[0], tokens[1], tokens[2], tokens[3]);
                adMap.put(addressBook.getId(), addressBook);
            });
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
        }

        return adMap;
    }

    public static ArrayList<LibraryData> parseLibraryCsv(String filename) {

        ArrayList<LibraryData> libList = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(dataDirectory + filename))) {
            stream.forEach((line) -> {
                String[] tokens = line.split(", ");
                LibraryData libData = new LibraryData(tokens[0], tokens[1]);
                libList.add(libData);
            });
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
        }

        return libList;
    }
}
