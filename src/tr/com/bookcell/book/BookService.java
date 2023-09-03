package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.publisher.Publisher;

import java.util.List;

public interface BookService {

    void add(String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber, boolean isAvailable);
    List<Book> getAll();
    void remove(String name, Integer authorId);
    void setAvailable(String name, Integer authorId, boolean isAvailable);
}
