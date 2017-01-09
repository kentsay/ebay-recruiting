package de.ebay.recruiting.csv;


import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;
import de.ebay.recruiting.csv.util.DateUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSolver {

    public int getNumberOfFemales(HashMap<String, AddressBook> adMap) {
        int numberOfFemales = 0;
        for (AddressBook person: adMap.values()) {
            if (person.getGender().equals("Female"))
                numberOfFemales++;
        }
        return numberOfFemales;
    }

    public ArrayList<AddressBook> getOldestPerson(HashMap<String, AddressBook> adMap) {
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
        return oldestPerson;
    }

    public HashMap<String, Integer> getNumberOfBooksRentByPerson(HashMap<String, AddressBook> adMap, ArrayList<LibraryData> libList) {
        HashMap<String, Integer> bookRentMap = new HashMap<>();
        for(LibraryData data: libList) {
            String id = data.getRenterId();
            if (!id.equals("NULL")) {
                if (bookRentMap.containsKey(id)) {
                    bookRentMap.put(id, bookRentMap.get(id)+1);
                } else {
                    bookRentMap.put(id, 1);
                }
            } else continue;
        }
        return bookRentMap;
    }

    public String getBooksRentByPerson(HashMap<String, AddressBook> adMap, ArrayList<LibraryData> libList) {
        HashMap<String, ArrayList<String>> bookRentMap = new HashMap<>();

        for(LibraryData data: libList) {
            String id = data.getRenterId();
            if (!id.equals("NULL")) {
                if (bookRentMap.containsKey(id)) {
                    ArrayList<String> rentedBookList = bookRentMap.get(id);
                    rentedBookList.add(data.getBookName());
                    bookRentMap.put(id, rentedBookList);
                } else {
                    ArrayList<String> rentedBookList = new ArrayList<>();
                    rentedBookList.add(data.getBookName());
                    bookRentMap.put(id, rentedBookList);
                }
            } else continue;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(
                "<html>" +
                    "<body>" +
                        "<table>" +
                        "<tr>" +
                            "<th>Name</th>" +
                            "<th>Borrowed Books</th>" +
                        "</tr>");

        bookRentMap.forEach((k,v) -> {
            sb.append("<tr><td>" + adMap.get(k).getName() + "</td><td>" + v + "</td></tr>");
        });
        sb.append("</table></body></html>");
        return sb.toString();
    }

    public ArrayList<String> getBooksNotRented(ArrayList<LibraryData> libData) {
        ArrayList<String> bookNotRent = new ArrayList<>();
        for (LibraryData data: libData) {
            if (data.getRenterId().equals("NULL")) {
                bookNotRent.add(data.getBookName());
            }
        }
        return bookNotRent;
    }

    public long getDaysOlder(AddressBook person1, AddressBook person2) {
        long days = DateUtil.getDifferenceInDays(person1.getBirthday(), person2.getBirthday());
        return days;
    }

}
