package tr.com.bookcell.book;

import tr.com.bookcell.author.*;
import tr.com.bookcell.publisher.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;

public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void add(String name, String authorName, String authorSurname, String publisherName, String genre, int publicationYear, int pageNumber, boolean isAvailable) {
        String formattedName = capitalizeFirst(name);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        String formattedPublisherName = capitalizeFirst(publisherName);
        AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
        AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);
        PublisherRepository defaultPublisherRepository = new DefaultPublisherRepository();
        PublisherService defaultPublisherService = new DefaultPublisherService(defaultPublisherRepository);
        Publisher publisher = new Publisher();
        Author author = new Author();
        author = defaultAuthorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        publisher = defaultPublisherService.getByName(formattedPublisherName);
        Book book = new Book(formattedName, author.getId(), publisher.getId(), genre, publicationYear, pageNumber, isAvailable);
        for (Book tempBook : getAll()) {
            if (tempBook.getName().equals(book.getName()) && tempBook.getAuthorId().equals(book.getAuthorId()) && tempBook.getPublisherId().equals(book.getPublisherId())) {
                System.out.println("THERE IS ALREADY " + formattedName + " IN BOOKS LIST");
                break;
            }
            bookRepository.add(book);
        }
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getByName(String name) {
        String formattedName = capitalizeFirst(name);
        return bookRepository.getByName(formattedName);
    }

    @Override
    public Book getByNameAndAuthor(String name, String authorName, String authorSurname) {
        String formattedName = capitalizeFirst(name);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Author author = new Author();
        AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
        AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);
        author = defaultAuthorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        return bookRepository.getByNameAndAuthor(formattedName, author.getId());
    }


    @Override
    public void remove(String name, String authorName, String authorSurname) {
        String formattedName = capitalizeFirst(name);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Author author = new Author();
        AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
        AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);
        author = defaultAuthorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        for (Book tempBook : getAll()) {
            if (tempBook.getName().equals(formattedName) && tempBook.getAuthorId().equals(author.getId())) {
                bookRepository.remove(formattedName, author.getId());
                break;
            }
            System.out.println("THERE IS NO" + formattedName + " IN BOOKS LIST!");
        }
    }

    @Override
    public void setAvailable(String name, Integer authorId, boolean isAvailable) {
        String formattedName = capitalizeFirst(name);
        bookRepository.setAvailable(formattedName, authorId, isAvailable);
    }


}
