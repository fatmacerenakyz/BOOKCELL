package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.customer.Customer;

import java.util.List;

public interface BasketRepository {
    void add(Customer customer, Book book);
    void remove(Customer customer, Book book);
    List<Book> get(Customer customer);
}
