package tr.com.bookcell.user;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.payment.Payment;
import tr.com.bookcell.reservation.Reservation;

import java.time.LocalDate;
import java.util.List;

public class Customer extends User {
    //optional parameters
    private List<Book> books;
    private LocalDate registrationDate;
    private List<Reservation> reservations;
    private List<Payment> payments;

    public Customer() {
    }

    public Customer(String id, String name, String surname, String userName, String email, String password, List<Book> books, LocalDate registrationDate, List<Reservation> reservations, List<Payment> payments) {
        super(id, name, surname, userName, email, password);
        this.books = books;
        this.registrationDate = registrationDate;
        this.reservations = reservations;
        this.payments = payments;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
