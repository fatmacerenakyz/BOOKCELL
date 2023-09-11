package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.customer.Customer;

import java.util.List;

public interface BasketService {
    void add(String customerEmail, String bookName, String authorName, String authorSurname);
    void remove(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Basket> getByCustomerEmail(String customerEmail);
}
