package tr.com.bookcell.author;

import tr.com.bookcell.BaseRepository;

import java.util.List;

public interface AuthorRepository extends BaseRepository {

    void add(Author author);

    List<Author> getAll();

    List<Author> getByName(String name);
    void remove(Author author);

}
