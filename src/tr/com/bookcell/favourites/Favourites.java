package tr.com.bookcell.favourites;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

public class Favourites {
    private String id;
    private Customer customer;
    private Book book;

    public Favourites() {
    }

    public Favourites(String id, Customer customer, Book book) {
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

    public void setBook(Book book) {
        this.book = book;
    }

}
