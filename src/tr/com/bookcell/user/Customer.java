package tr.com.bookcell.user;

import java.time.LocalDate;
import java.util.Objects;

public class Customer extends User {
    private String name;
    private String surName;
    private LocalDate registrationDate;

    public Customer() {
    }

    public Customer(String id, String email, String password, String name, String surName, LocalDate registrationDate) {
        super(id, email, password);
        this.name = name;
        this.surName = surName;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
        return Objects.equals(name, customer.name) && Objects.equals(surName, customer.surName) && Objects.equals(registrationDate, customer.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surName, registrationDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
