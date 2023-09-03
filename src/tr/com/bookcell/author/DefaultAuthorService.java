package tr.com.bookcell.author;

import java.util.List;

public class DefaultAuthorService implements AuthorService{
    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void add(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public List<Author> getByName(String name) {
        return authorRepository.getByName(name);

    }

    @Override
    public void remove(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.remove(author);
    }
}
