package de.ebay.recruiting.csv.domain;


public class LibraryData {

    public String bookName;
    public String renterId;

    public LibraryData(String bookName, String renterId) {
        this.bookName = bookName;
        this.renterId = renterId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }

    @Override
    public String toString() {
        return "LibraryData{" +
                "bookName='" + bookName + '\'' +
                ", renterId='" + renterId + '\'' +
                '}';
    }
}
