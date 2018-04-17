package ch.heigvd.res.labs_smtp.model.mail;

public class Person {
    private String firstName;
    private String lastName;
    private String address;

    public Person(String address) {
        this.address = address;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
}
