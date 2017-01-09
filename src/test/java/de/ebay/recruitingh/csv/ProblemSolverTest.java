package de.ebay.recruitingh.csv;


import de.ebay.recruiting.csv.ProblemSolver;
import de.ebay.recruiting.csv.domain.AddressBook;
import de.ebay.recruiting.csv.domain.LibraryData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProblemSolverTest {

    HashMap<String, AddressBook> adMap;
    ArrayList<LibraryData> libList;
    ProblemSolver ps;

    @Before
    public void init() {
        adMap = new HashMap<>();
        libList = new ArrayList<>();
        ps = new ProblemSolver();
    }

    @Test
    public void testDataValidationTrue() {
        AddressBook ad1 = new AddressBook("1", "Jachin", "Male", "1978-04-15");
        adMap.put("1", ad1);
        LibraryData lib1 = new LibraryData("The bible", "NULL");
        libList.add(lib1);
        assertTrue(ps.dataValidation(adMap, libList));
    }

    @Test
    public void testDataValidationFalse() {
        assertFalse(ps.dataValidation(adMap, libList));
    }

    @Test
    public void testNumberOfFemales() {
        AddressBook ad1 = new AddressBook("1", "Jessica", "Female", "1982-11-5");
        AddressBook ad2 = new AddressBook("2", "Jachin", "Male", "1978-04-15");
        adMap.put("1", ad1);
        adMap.put("2", ad2);
        assertEquals(ps.getNumberOfFemales(adMap), 1);
    }

    @Test
    public void testNumberOfFemalesNone() {
        AddressBook ad1 = new AddressBook("1", "Jachin", "Male", "1978-04-15");
        adMap.put("1", ad1);
        assertEquals(ps.getNumberOfFemales(adMap), 0);
    }

    @Test
    public void testSingleOldestPerson() {
        AddressBook ad1 = new AddressBook("1", "Jessica", "Female", "1982-11-05");
        AddressBook ad2 = new AddressBook("2", "Jachin", "Male", "1978-04-15");
        AddressBook ad3 = new AddressBook("3", "Marco", "Male", "1978-04-15");
        AddressBook ad4 = new AddressBook("4", "Vincent", "Male", "1950-03-19");
        adMap.put("1", ad1);
        adMap.put("2", ad2);
        adMap.put("3", ad3);
        adMap.put("4", ad4);
        assertEquals(ps.getOldestPerson(adMap).size(), 1);
        assertEquals(ps.getOldestPerson(adMap).get(0).getName(), "Vincent");
    }

    @Test
    public void testMutlipleOldestPerson() {
        AddressBook ad1 = new AddressBook("1", "Jessica", "Female", "1982-11-05");
        AddressBook ad2 = new AddressBook("2", "Jachin", "Male", "1978-04-15");
        AddressBook ad3 = new AddressBook("3", "Marco", "Male", "1978-04-15");
        adMap.put("1", ad1);
        adMap.put("2", ad2);
        adMap.put("3", ad3);
        assertEquals(ps.getOldestPerson(adMap).size(), 2);
        assertEquals(ps.getOldestPerson(adMap).get(0).getName(), "Jachin");
        assertEquals(ps.getOldestPerson(adMap).get(1).getName(), "Marco");

    }

    @Test
    public void testNumberOfBooksRentByPerson() {
        LibraryData lib1 = new LibraryData("The bible", "1");
        LibraryData lib2 = new LibraryData("Harry Potter", "1");
        libList.add(lib1);
        libList.add(lib2);
        HashMap<String, Integer> result = ps.getNumberOfBooksRentByPerson(libList);
        assertEquals((int)result.get(lib1.getRenterId()), 2);
    }

    @Test
    public void testBookNotRented() {
        LibraryData lib1 = new LibraryData("The bible", "NULL");
        LibraryData lib2 = new LibraryData("Harry Potter", "NULL");
        libList.add(lib1);
        libList.add(lib2);
        assertEquals(ps.getBooksNotRented(libList).size(), 2);
    }


    @Test
    public void testBookRentByPersonHTML() {
        AddressBook ad1 = new AddressBook("1", "Jessica", "Female", "1982-11-05");
        AddressBook ad2 = new AddressBook("2", "Jachin", "Male", "1978-04-15");
        adMap.put("1", ad1);
        adMap.put("2", ad2);
        LibraryData lib1 = new LibraryData("The bible", "1");
        LibraryData lib2 = new LibraryData("Harry Potter", "2");
        libList.add(lib1);
        libList.add(lib2);

        assertEquals(ps.getBooksRentByPersonHTML(adMap, libList),
                "<html><body><table><tr><th>Name</th><th>Borrowed Books</th></tr><tr><td>Jessica</td><td>[The bible]</td></tr><tr><td>Jachin</td><td>[Harry Potter]</td></tr></table></body></html>");
    }

    @Test
    public void testDaysOldersPositive() {
        AddressBook ad1 = new AddressBook("1", "Marco", "Male", "1978-04-15");
        AddressBook ad2 = new AddressBook("2", "Jessica", "Female", "1982-11-05");
        assertEquals(ps.getDaysOlder(ad1, ad2), 1665);
    }

    @Test
    public void testDaysOldersNegative() {
        AddressBook ad1 = new AddressBook("1", "Jessica", "Female", "1982-11-05");
        AddressBook ad2 = new AddressBook("2", "Marco", "Male", "1978-04-15");
        assertEquals(ps.getDaysOlder(ad1, ad2), -1665);
    }
}
