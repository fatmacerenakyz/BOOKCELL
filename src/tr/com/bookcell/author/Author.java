package tr.com.bookcell.author;

import tr.com.bookcell.branch.Branch;
import tr.com.bookcell.book.Book;

import java.util.List;
import java.util.Objects;

public class Author {
    private String name;
    private String surname;
    private String id;
    private List<Book> books;
    private List<Branch> branches;
    public Author(){}
    public Author(String name, String surname, String id, List<Book> books, List<Branch> branches) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.books = books;
        this.branches = branches;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Author) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.books, that.books) &&
                Objects.equals(this.branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, books, branches);
    }

    @Override
    public String toString() {
        return "tr.com.bookcell.author.Author[" +
                "idNumber=" + id + ", " +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "book=" + books + ", " +
                "branch=" + branches + ']';
    }

}
