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
    public Author getByNameAndSurname(String name, String surname) {
        return authorRepository.getByNameAndSurname(name, surname);
    }

    @Override
    public void remove(String name, String surname) {
        authorRepository.remove(name, surname);
    }
}
