package tr.com.bookcell.favourite;

import tr.com.bookcell.BaseRepository;

public interface FavouriteRepository extends BaseRepository {
    void add(Favourite favourite);
    void remove(Integer customerId, Integer bookId);
}
