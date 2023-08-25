package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.branch.Branch;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    void delete(String id);
    Book getWithId(String id);
    List<Book> getAll();
    Book getWithName(String name);
    Book getWithAuthor(Author author);
    Book getWithBranch(Branch branch);
}
