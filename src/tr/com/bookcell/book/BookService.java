package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;

import java.util.List;

public interface BookService {
    void add(Book book);

    void delete(String id);

    Book getWithId(String id);

    List<Book> getAll();

    Book getWithName(String name);

    Book getWithAuthor(Author author);

}
