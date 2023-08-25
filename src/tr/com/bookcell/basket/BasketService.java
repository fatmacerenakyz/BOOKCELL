package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.util.List;

public interface BasketService {
    void add(Customer customer, Book book);
    void remove(Customer customer, Book book);
    List<Book> get(Customer customer);
}
