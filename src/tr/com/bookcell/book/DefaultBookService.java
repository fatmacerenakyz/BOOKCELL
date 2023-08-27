package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;

import java.util.List;

public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void add(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void delete(String id) {
        bookRepository.delete(id);
    }

    @Override
    public Book getWithId(String id) {
        return bookRepository.getWithId(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book getWithName(String name) {
        return bookRepository.getWithName(name);
    }

    @Override
    public Book getWithAuthor(Author author) {
        return bookRepository.getWithAuthor(author);
    }

}
