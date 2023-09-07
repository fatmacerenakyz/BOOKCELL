package tr.com.bookcell.basket;

import tr.com.bookcell.BaseRepository;
import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.customer.Customer;

import java.util.List;

public interface BasketRepository extends BaseRepository {
    void add(Basket basket);
    void remove(Integer customerId, Integer bookId);
    List<Basket> getByCustomerId(Integer customerId);
}
