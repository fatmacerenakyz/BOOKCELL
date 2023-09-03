package tr.com.bookcell.user.customer;

import tr.com.bookcell.user.User;

import java.time.LocalDate;
import java.util.Objects;

public class Customer extends User {
    private String name;
    private String surname;
    private LocalDate registrationDate;

    public Customer() {
    }

    public Customer(String email, String password, String name, String surname, LocalDate registrationDate) {
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.registrationDate = registrationDate;
    }

    public Customer(String id, String email, String password, String name, String surname, LocalDate registrationDate) {
        super(id, email, password);
        this.name = name;
        this.surname = surname;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(registrationDate, customer.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, registrationDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surName='" + surname + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
