package tr.com.bookcell.favourite;

public interface FavouriteService {
    void add(Integer customerId, String bookName, String authorName, String authorSurname);
    void remove(Integer customerId, String bookName, String authorName, String authorSurname);
}
