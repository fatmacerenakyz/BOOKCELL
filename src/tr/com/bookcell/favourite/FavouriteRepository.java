package tr.com.bookcell.favourite;

import tr.com.bookcell.BaseRepository;
import tr.com.bookcell.reservation.Reservation;

import java.util.List;

public interface FavouriteRepository extends BaseRepository {
    void add(Favourite favourite);
    void remove(Integer customerId, Integer bookId);
    List<Favourite> getByCustomerId(Integer customerId);
}
