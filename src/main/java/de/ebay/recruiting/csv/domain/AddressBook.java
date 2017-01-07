package de.ebay.recruiting.csv.domain;


public class AddressBook {

    public String id;
    public String name;
    public String gender;
    public String birthday;

    public AddressBook(String id, String name, String gender, String birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
