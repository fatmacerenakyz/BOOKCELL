package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.customer.Customer;

import java.util.List;

public interface BasketService {
    void add(Integer customerId, String bookName, String authorName, String authorSurname);
    void remove(Integer customerId, String bookName, String authorName, String authorSurname);
    List<Basket> getByCustomerId(Integer customerId);
}
