package tr.com.bookcell.favourite;

import java.util.List;

public interface FavouriteService {
    void add(Integer customerId, String bookName, String authorName, String authorSurname);
    void remove(Integer customerId, String bookName, String authorName, String authorSurname);
    List<Favourite> getByCustomerId(Integer customerId);
}
