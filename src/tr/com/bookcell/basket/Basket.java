package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.util.Objects;

public class Basket {
    private String id;
    private Customer customer;
    private Book book;

    public Basket() {
    }

    public Basket(String id, Customer customer, Book book) {
        this.id = id;
        this.customer = customer;
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

    public Book getBook() {
        return book;
    }

    public void setBooks(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket basket)) return false;
        return Objects.equals(customer, basket.customer) && Objects.equals(book, basket.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, book);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "customer='" + customer + '\'' +
                ", book=" + book +
                '}';
    }
}
