package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.publisher.Publisher;

import java.util.List;

public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

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
    public void remove(String name, Integer authorId) {
        Book book = new Book(name, authorId);
        bookRepository.remove(book);
    }

    @Override
    public void setAvailable(String name, Integer authorId, boolean isAvailable) {
        Book book = new Book(name, authorId, isAvailable);
        bookRepository.setAvailable(book);
    }
}
