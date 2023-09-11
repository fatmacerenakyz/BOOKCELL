package tr.com.bookcell.favourite;

import java.util.List;

public interface FavouriteService {
    void add(String customerEmail, String bookName, String authorName, String authorSurname);
    void remove(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Favourite> getByCustomerEmail(String customerEmail);
}
