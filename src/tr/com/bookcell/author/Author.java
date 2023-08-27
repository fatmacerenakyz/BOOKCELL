package tr.com.bookcell.author;

import tr.com.bookcell.book.Book;

import java.util.List;
import java.util.Objects;

public class Author {
    private String id;
    private String name;
    private String surname;
    private List<Book> books;

    public Author() {
    }

    public Author(String id, String name, String surname, List<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Author) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, books);
    }

    @Override
    public String toString() {
        return "tr.com.bookcell.author.Author[" +
                "idNumber=" + id + ", " +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "book=" + books + ", " + ']';
    }

}
