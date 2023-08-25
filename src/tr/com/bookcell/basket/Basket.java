package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.util.List;
import java.util.Objects;

public class Basket {
    private Customer customer;
    private List<Book> books;

    public Basket() {
    }

    public Basket(Customer customer, List<Book> books) {
        this.customer = customer;
        this.books = books;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket basket)) return false;
        return Objects.equals(customer, basket.customer) && Objects.equals(books, basket.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, books);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "customer='" + customer + '\'' +
                ", books=" + books +
                '}';
    }
}
