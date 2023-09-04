package tr.com.bookcell.user.customer;

import tr.com.bookcell.BaseRepository;
import tr.com.bookcell.book.Book;

public interface CustomerRepository extends BaseRepository {
    void add(Customer customer);
}
