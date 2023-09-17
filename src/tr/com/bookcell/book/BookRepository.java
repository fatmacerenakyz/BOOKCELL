package tr.com.bookcell.book;

import tr.com.bookcell.BaseRepository;

import java.util.List;

public interface BookRepository extends BaseRepository {
    void add(Book book);

    List<Book> getAll();

    List<Book> getByName(String name);
    Book getByNameAndAuthor(String name, Integer authorId);

    void remove(String name, Integer authorId);

    void setAvailable(Integer bookId, boolean isAvailable);
    Book getById(Integer id);

}
