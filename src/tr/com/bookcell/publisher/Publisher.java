package tr.com.bookcell.publisher;
import tr.com.bookcell.author.Author;
import tr.com.bookcell.book.Book;
import tr.com.bookcell.branch.Branch;
import java.util.List;
import java.util.Objects;

public class Publisher {
    private String name;
    private List<Author> authors;
    private List<Book> books;
    private List<Branch> branches;
    public Publisher(){}
    public Publisher(String name, List<Author> authors, List<Book> books, List<Branch> branches) {
        this.name = name;
        this.authors = authors;
        this.books = books;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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
        var that = (Publisher) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.authors, that.authors) &&
                Objects.equals(this.books, that.books) &&
                Objects.equals(this.branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authors, books, branches);
    }

    @Override
    public String toString() {
        return "tr.com.bookcell.publisher.Publisher[" +
                "name=" + name + ", " +
                "author=" + authors + ", " +
                "book=" + books + ", " +
                "branch=" + branches + ", " + ']';
    }

}
