package tr.com.bookcell.user.customer;

import tr.com.bookcell.BaseRepository;
import tr.com.bookcell.book.Book;

import java.util.List;

public interface CustomerRepository extends BaseRepository {
    void add(Customer customer);
    void remove(String email);
    List<Customer> getAll();
    Customer getByEmail(String email);
}
