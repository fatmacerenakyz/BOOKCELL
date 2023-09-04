package tr.com.bookcell.book;

import tr.com.bookcell.author.*;

import java.util.List;

public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
    AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);
    Author author = new Author();

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void add(String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber, boolean isAvailable) {
        Book book = new Book(name, authorId, publisherId, genre, publicationYear, pageNumber, isAvailable);
        bookRepository.add(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getByName(String name) {
        return bookRepository.getByName(name);
    }

    @Override
    public Book getByNameAndAuthor(String name, String authorName, String authorSurname) {
        author = defaultAuthorService.getByNameAndSurname(authorName, authorSurname);
        return bookRepository.getByNameAndAuthor(name, author.getId());
    }


    @Override
    public void remove(String name, String authorName, String authorSurname) {
        author = defaultAuthorService.getByNameAndSurname(authorName, authorSurname);
        bookRepository.remove(name, author.getId());
    }

    @Override
    public void setAvailable(String name, Integer authorId, boolean isAvailable) {
        bookRepository.setAvailable(name, authorId, isAvailable);
    }


}
