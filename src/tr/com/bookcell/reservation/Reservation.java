package tr.com.bookcell.reservation;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private String id;
    private Customer customer;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;
    private Book book;

    public Reservation() {
    }

    public Reservation(String id, Customer customer, LocalDateTime startDate, LocalDateTime expiryDate, Book book) {
        this.id = id;
        this.customer = customer;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(startDate, that.startDate) && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, startDate, expiryDate, book);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                ", book=" + book +
                '}';
    }
}
