package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.author.AuthorService;
import tr.com.bookcell.publisher.Publisher;
import tr.com.bookcell.publisher.PublisherService;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public DefaultBookService(BookRepository bookRepository, AuthorService authorService, PublisherService publisherService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }


    @Override
    public boolean add(String name, String authorName, String authorSurname, String publisherName, String genre, int publicationYear, int pageNumber, boolean isAvailable) {
        String formattedName = capitalizeForBookName(name);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        String formattedPublisherName = capitalizeForMultipleStrings(publisherName);
        String formattedGenre = capitalizeForMultipleStrings(genre);

        Author author = authorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        Publisher publisher = publisherService.getByName(formattedPublisherName);
        if (author != null && publisher != null) {
            for (Book tempBook : getAll()) {
                if (tempBook.getName().equals(formattedName) && tempBook.getAuthorId().equals(author.getId())) {
                    System.out.println("THERE IS ALREADY " + formattedName + " IN BOOKS LIST");
                    return false;
                }
            }
            Book book = new Book(formattedName, author.getId(), publisher.getId(), formattedGenre, publicationYear, pageNumber, isAvailable);
            bookRepository.add(book);
        }
        return true;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getByName(String name) {
        String formattedName = capitalizeForBookName(name);
        for (Book tempBook : getAll()) {
            if (tempBook.getName().equals(formattedName))
                return bookRepository.getByName(formattedName);
        }
        return null;
    }

    @Override
    public Book getByNameAndAuthor(String name, String authorName, String authorSurname) {
        String formattedName = capitalizeForBookName(name);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        Author author = authorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        if (author != null) {
            for (Book tempBook : getAll()) {
                if (tempBook.getName().equals(formattedName) && tempBook.getAuthorId().equals(author.getId())) {
                    return bookRepository.getByNameAndAuthor(formattedName, author.getId());
                }
            }
        }
        return null;
    }


    @Override
    public void remove(String name, String authorName, String authorSurname) {
        String formattedName = capitalizeForBookName(name);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        Author author = authorService.getByNameAndSurname(formattedAuthorName, formattedAuthorSurname);
        if (author != null) {
            for (Book tempBook : getAll()) {
                if (tempBook.getName().equals(formattedName) && tempBook.getAuthorId().equals(author.getId())) {
                    bookRepository.remove(formattedName, author.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO" + formattedName + " IN BOOKS LIST!");
        }
    }

    @Override
    public void setAvailable(Integer bookId, boolean isAvailable) {
        bookRepository.setAvailable(bookId, isAvailable);
    }

}
