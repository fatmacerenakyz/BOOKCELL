package tr.com.bookcell.basket;

import tr.com.bookcell.BaseRepository;

import java.util.List;

public interface BasketRepository extends BaseRepository {
    void add(Basket basket);
    void remove(Integer customerId, Integer bookId);
    List<Basket> getByCustomer(Integer customerId);
    List<Basket> getAll();
}
