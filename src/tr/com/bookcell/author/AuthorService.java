package tr.com.bookcell.author;

import java.util.List;

public interface AuthorService {
    void add(String name, String surname);
    List<Author> getAll();
    List<Author> getByName(String name);
    void remove(String name, String surname);
}
