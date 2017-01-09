package de.ebay.recruiting.csv;

import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;
import de.ebay.recruiting.csv.util.CsvUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        //default input data
        String adBookFileName = "address-book.csv";
        String libFileName = "library.csv";

        runProgram(adBookFileName, libFileName);
    }

    public static void runProgram(String addressBookFile, String libraryFile) {
        //Program keeps running till this condition updates.
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        HashMap<String, AddressBook> adMap = CsvUtil.parseAddressBookCsv(addressBookFile);
        ArrayList<LibraryData> libList     = CsvUtil.parseLibraryCsv(libraryFile);

        int userOption = 0;
        String newAddressBook, newLibrary;
        while(keepRunning) {
            userOption = showOptions();
            switch(userOption) {
                case 1:
                    solveProblem(adMap, libList);
                    break;
                case 2:
                    System.out.println("Please enter new addressBook csv file: ");
                    newAddressBook = scanner.nextLine();
                    adMap = CsvUtil.parseAddressBookCsv(newAddressBook);
                    solveProblem(adMap, libList);
                    break;
                case 3:
                    System.out.println("Please enter new library csv file: ");
                    newLibrary = scanner.nextLine();
                    libList = CsvUtil.parseLibraryCsv(newLibrary);
                    solveProblem(adMap, libList);
                    break;
                case 4:
                    System.out.println("Please enter new addressBook csv file: ");
                    newAddressBook = scanner.nextLine();
                    System.out.println("Please enter new library csv file: ");
                    newLibrary = scanner.nextLine();
                    adMap = CsvUtil.parseAddressBookCsv(newAddressBook);
                    libList = CsvUtil.parseLibraryCsv(newLibrary);
                    solveProblem(adMap, libList);
                    break;
                case 5:
                    System.out.println("Good-Bye");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option! Please re-enter your option again.");
                    break;
            }
        }
    }

    public static int showOptions() {
        Scanner scanner = new Scanner(System.in);
        int intChoice;
        String strChoice;

        System.out.println("\n-------------------------------------");
        System.out.println("|-----------------------------------|");
        System.out.println("| Main Menu:                        |");
        System.out.println("|-----------------------------------|");
        System.out.println("|                                   |");
        System.out.println("| 1.) Solve Problem                 |");
        System.out.println("| 2.) Select new address-book file  |");
        System.out.println("| 3.) Select new library file       |");
        System.out.println("| 4.) Select new file for both      |");
        System.out.println("| 5.) Exit!                         |");
        System.out.println("|                             v 1.0 |");
        System.out.println("-------------------------------------");

        System.out.print("Please choose a option: ");
        strChoice = scanner.next();
        try {
            intChoice = Integer.parseInt(strChoice);
        }
        catch (NumberFormatException e) {
            intChoice = -1;
        }
        return intChoice;
    }

    public static void solveProblem(HashMap<String, AddressBook> adMap, ArrayList<LibraryData> libList) {

        ProblemSolver ps = new ProblemSolver();

        if (ps.dataValidation(adMap, libList)) {
            System.out.println("* How many females are in the address book? ");
            System.out.println(ps.getNumberOfFemales(adMap));
            System.out.println("------------------------------");

            System.out.println("* Who is the oldest person in the address book?");
            ArrayList<AddressBook> oldestPerson = ps.getOldestPerson(adMap);
            oldestPerson.forEach((person) ->  {
                System.out.print(person.getName() + ", ");
            });
            System.out.println();
            System.out.println("------------------------------");

            System.out.println("* Who has rented how many books?");
            HashMap<String, Integer> bookRentMap = ps.getNumberOfBooksRentByPerson(libList);
            bookRentMap.forEach((k,v) -> {
                System.out.println(adMap.get(k).getName() + ": " + v);
            });
            System.out.println("------------------------------");

            System.out.println("* Which books have not been rented?");
            ArrayList<String> bookNotRent = ps.getBooksNotRented(libList);
            bookNotRent.forEach((book) -> {
                System.out.print(book + ", ");
            });
            System.out.println();
            System.out.println("------------------------------");

            AddressBook Jon = new AddressBook();
            AddressBook Paul = new AddressBook();

            for (AddressBook person: adMap.values()) {
                if (person.getName().equals("Jon"))
                    Jon = person;
                else if (person.getName().equals("Paul"))
                    Paul = person;
            }

            System.out.println("* How many days older is Jon than Paul?");
            System.out.println(ps.getDaysOlder(Jon, Paul));
            System.out.println("------------------------------");


            System.out.println("* Which person has borrowed which book?");
            System.out.println(ps.getBooksRentByPersonHTML(adMap, libList));
        } else {
            System.out.println("Error! Please make sure your input data exist and has content.");
        }
    }
}
