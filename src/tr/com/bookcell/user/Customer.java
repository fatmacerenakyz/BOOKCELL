package tr.com.bookcell.user;

import java.time.LocalDate;
import java.util.Objects;

public class Customer extends User {
    private LocalDate registrationDate;

    public Customer() {
    }

    public Customer(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Customer(String id, String name, String surname, String userName, String email, String password, LocalDate registrationDate) {
        super(id, name, surname, userName, email, password);
        this.registrationDate = registrationDate;
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
        return Objects.equals(registrationDate, customer.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), registrationDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "registrationDate=" + registrationDate +
                '}';
    }
}
