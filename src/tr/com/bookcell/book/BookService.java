package tr.com.bookcell.book;

import java.util.List;

public interface BookService {

    void add(String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber, boolean isAvailable);

    List<Book> getAll();

    List<Book> getByName(String name);
    Book getByNameAndAuthor(String name, String authorName, String authorSurname);

    void remove(String name, String authorName, String authorSurname);

    void setAvailable(String name, Integer authorId, boolean isAvailable);

}
