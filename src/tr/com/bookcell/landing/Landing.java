package tr.com.bookcell.landing;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Landing {
    private String id;
    private Customer customer;
    private Book book;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;

    public Landing() {

    }

    public Landing(String id, Customer customer, Book book, LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Landing landing)) return false;
        return Objects.equals(id, landing.id) && Objects.equals(customer, landing.customer) && Objects.equals(book, landing.book) && Objects.equals(pickUpDate, landing.pickUpDate) && Objects.equals(dropOffDate, landing.dropOffDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, book, pickUpDate, dropOffDate);
    }

    @Override
    public String toString() {
        return "Landing{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", book=" + book +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                '}';
    }
}
