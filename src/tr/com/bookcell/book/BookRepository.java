package tr.com.bookcell.book;

import tr.com.bookcell.BaseRepository;
import tr.com.bookcell.author.Author;
import tr.com.bookcell.publisher.Publisher;

import java.util.List;

public interface BookRepository extends BaseRepository {
    void add(Book book);
    List<Book> getAll();
    void remove(Book book);
    void setAvailable(Book book);
}
