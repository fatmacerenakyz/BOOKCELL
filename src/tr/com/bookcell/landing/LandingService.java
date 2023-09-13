package tr.com.bookcell.landing;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.customer.Customer;

import java.util.List;

public interface LandingService {
    void setPickUp(String customerEmail, String bookName, String authorName, String authorSurname);
    void setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate);
    List<Landing> getByCustomerAndBook(Customer customer, Book book);
    List<Landing> getAll();
}
