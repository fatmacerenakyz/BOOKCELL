package tr.com.bookcell.branch;

import tr.com.bookcell.book.Book;

import java.util.List;
import java.util.Objects;

public class Branch {
    private String name;
    private String address;
    private List<Book> books;

    public Branch() {
    }

    public Branch(String name, String address, List<Book> books) {
        this.name = name;
        this.address = address;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        var that = (Branch) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, books);
    }

    @Override
    public String toString() {
        return "tr.com.bookcell.branch.Branch[" +
                "name=" + name + ", " +
                "address=" + address + ", " +
                "book=" + books + ']';
    }

}
