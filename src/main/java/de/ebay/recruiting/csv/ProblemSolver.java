package de.ebay.recruiting.csv;


import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;
import de.ebay.recruiting.csv.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSolver {

    public static void getNumberOfFemales(HashMap<String, AddressBook> adMap) {
        int numberOfFemales = 0;
        for (AddressBook person: adMap.values()) {
            if (person.getGender().equals("Female"))
                numberOfFemales++;
        }
        System.out.println("How many females are in the address book? ");
        System.out.println(numberOfFemales);
        System.out.println("------------------------------");
    }

    public static void getOldestPerson(HashMap<String, AddressBook> adMap) {

        ArrayList<AddressBook> oldestPerson = new ArrayList<>();
        for (AddressBook person: adMap.values()) {
            if (oldestPerson.isEmpty()) {
                oldestPerson.add(person);
            } else {
                long days = DateUtil.getDifferenceInDays(oldestPerson.get(0).getBirthday(), person.getBirthday());
                if (days > 0) {
                    //older
                } else if (days < 0) {
                    //younger
                    oldestPerson = new ArrayList<>();
                    oldestPerson.add(person);
                } else if (days == 0) {
                    //same age, also the oldest person
                    oldestPerson.add(person);
                }
            }
        }
        System.out.println("Who is the oldest person in the address book?");
        oldestPerson.forEach((person) ->  {
            System.out.println(person.getName() + ", ");
        });
        System.out.println("------------------------------");

    }

    public static void getNumberOfBooksRentByPerson(HashMap<String, AddressBook> adMap, ArrayList<LibraryData> libList) {

        HashMap<String, Integer> bookRentMap = new HashMap<>();
        for(LibraryData data: libList) {
            String id = data.getRenterId();
            if (!id.equals("NULL")) {
                if (bookRentMap.containsKey(id)) {
                    bookRentMap.put(id, bookRentMap.get(id)+1);
                } else {
                    bookRentMap.put(data.getRenterId(), 1);
                }
            } else continue;
        }

        System.out.println("Who has rented how many books?");
        bookRentMap.forEach((k,v) -> {
            System.out.println(adMap.get(k).getName() + " has rented " + v + " books");
        });
        System.out.println("------------------------------");
    }

    public static void getBooksNotRented(ArrayList<LibraryData> libData) {
        ArrayList<String> bookNotRent = new ArrayList<>();
        for (LibraryData data: libData) {
            if (data.getRenterId().equals("NULL")) {
                bookNotRent.add(data.getBookName());
            }
        }

        System.out.println("Which books have not been rented?");
        bookNotRent.forEach((book) -> {
            System.out.print(book + ", ");
        });
        System.out.println();
        System.out.println("------------------------------");

    }

    public static void getDaysOlder(AddressBook person1, AddressBook person2) {
        long days = DateUtil.getDifferenceInDays(person1.getBirthday(), person2.getBirthday());
        System.out.println("How many days older is Jon than Paul?");
        System.out.println(days);
        System.out.println("------------------------------");
    }

}
