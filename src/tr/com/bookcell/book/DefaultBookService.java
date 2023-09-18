package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.author.AuthorService;
import tr.com.bookcell.publisher.Publisher;
import tr.com.bookcell.publisher.PublisherService;

import java.util.List;
import java.util.Locale;

import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

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
    public boolean add(String name, String authorName, String authorSurname, String publisherName, String genre, int publicationYear, int pageNumber) {
        if(!isEnglish(name)||!isEnglish(authorName)||!isEnglish(authorSurname)||!isEnglish(publisherName)||!isEnglish(genre)){
            System.out.println(ansiColorRed()+"ENGLISH CHARACTERS ONLY."+ansiColorReset());
            return false;
        }
        Author author = authorService.getByNameAndSurname(authorName, authorSurname);
        Publisher publisher = publisherService.getByName(publisherName);
        if (author == null || publisher == null) {
            if (author == null)
                System.out.println(ansiColorRed() + "THERE IS NO " + authorName+ " "+authorSurname+ " IN AUTHORS LIST!" + ansiColorReset());
            else
                System.out.println(ansiColorRed() + "THERE IS NO " + publisherName + " IN PUBLISHERS LIST!" + ansiColorReset());
            return false;
        }

        for (Book tempBook : getAll()) {
            if (tempBook.getName().equals(name.toUpperCase(Locale.ENGLISH)) && tempBook.getAuthorId().equals(author.getId())) {
                System.out.println(ansiColorRed() + "THERE IS ALREADY " + name + " IN BOOKS LIST" + ansiColorReset());
                return false;
            }
        }
        Book book = new Book(name.toUpperCase(Locale.ENGLISH), author.getId(), publisher.getId(), genre.toUpperCase(Locale.ENGLISH), publicationYear, pageNumber, true);
        bookRepository.add(book);
        return true;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public List<Book> getByName(String name) {
        for (Book tempBook : getAll()) {
            if (tempBook.getName().equals(name.toUpperCase(Locale.ENGLISH)))
                return bookRepository.getByName(name.toUpperCase(Locale.ENGLISH));
        }
        return null;
    }

    @Override
    public Book getByNameAndAuthor(String name, String authorName, String authorSurname) {

        Author author = authorService.getByNameAndSurname(authorName, authorSurname);
        if (author != null) {
            for (Book tempBook : getAll()) {
                if (tempBook.getName().equals(name.toUpperCase(Locale.ENGLISH)) && tempBook.getAuthorId().equals(author.getId())) {
                    return bookRepository.getByNameAndAuthor(name.toUpperCase(Locale.ENGLISH), author.getId());
                }
            }
        }
        return null;
    }


    @Override
    public void remove(String name, String authorName, String authorSurname) {

        Author author = authorService.getByNameAndSurname(authorName, authorSurname);
        if (author != null) {
            for (Book tempBook : getAll()) {
                if (tempBook.getName().equals(authorName.toUpperCase(Locale.ENGLISH)) && tempBook.getAuthorId().equals(author.getId())) {
                    bookRepository.remove(name.toUpperCase(Locale.ENGLISH), author.getId());
                    return;
                }
            }
            System.out.println(ansiColorRed() + "THERE IS NO" + name + " IN BOOKS LIST!" + ansiColorReset());
        }
    }

    @Override
    public void setAvailable(Integer bookId, boolean isAvailable) {
        bookRepository.setAvailable(bookId, isAvailable);
    }

    @Override
    public Book getById(Integer id) {
        for (Book temp : getAll()) {
            if (temp.getId().equals(id)) {
                return bookRepository.getById(id);
            }
        }
        return null;
    }

}
