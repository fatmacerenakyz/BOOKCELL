package tr.com.bookcell.user.customer;

import tr.com.bookcell.user.User;

import java.time.LocalDate;
import java.util.Objects;

public class Customer extends User {
    private String name;
    private String surname;
    private LocalDate registrationDate;
    private String email;

    public Customer() {
    }

    public Customer(String password, String name, String surname, LocalDate registrationDate, String email) {
        super(password);
        this.name = name;
        this.surname = surname;
        this.registrationDate = registrationDate;
        this.email = email;
    }

    public Customer(Integer id, String password, String name, String surname, LocalDate registrationDate, String email) {
        super(id, password);
        this.name = name;
        this.surname = surname;
        this.registrationDate = registrationDate;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && Objects.equals(registrationDate, customer.registrationDate) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, registrationDate, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id = '" + getId() + "'" +
                ", password = '" + getPassword() + "'" +
                ", name ='" + name + '\'' +
                ", surname ='" + surname + '\'' +
                ", registrationDate =" + registrationDate +
                ", email ='" + email + '\'' +
                '}';
    }
}
