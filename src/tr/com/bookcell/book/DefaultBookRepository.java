package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultBookRepository implements BookRepository {
    public static List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        books.add(book);
        System.out.println("id: " + book.getId() + " name: " + book.getName() + " was added.");
    }

    @Override
    public void delete(String id) {
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                books.remove(book);
                System.out.println("id: " + book.getId() + " name: " + book.getName() + " was deleted.");
                return;
            }
        }
    }

    public Book getWithId(String id) {
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAll() {
        return books;
    }

    @Override
    public Book getWithName(String name) {
        for (Book book : books) {
            if (Objects.equals(book.getName(), name)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book getWithAuthor(Author author) {
        for (Book book : books) {
            if (Objects.equals(book.getAuthor(), author)) {
                return book;
            }
        }
        return null;
    }
}
