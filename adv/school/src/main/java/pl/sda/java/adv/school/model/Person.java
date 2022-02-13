package pl.sda.java.adv.school.model;

import java.time.LocalDate;

public class Person implements Comparable<Person>{
    private String id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", address=" + address +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int lastNameResult = lastName.compareTo(o.lastName);
        if (lastNameResult != 0) {
            return lastNameResult;
        }
        return firstName.compareTo(o.firstName);
    }
}
