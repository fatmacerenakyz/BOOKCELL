package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;
import tr.com.bookcell.branch.Branch;
import tr.com.bookcell.publisher.Publisher;

import java.util.Objects;

public class Book {
    private String id;
    private String name;
    private Author author;
    private Publisher publisher;
    private String genre;
    private int publicationYear;
    private int pageNumber;
    private Branch branch;
    private boolean isAvailable;
    private boolean isFav;

    public Book() {}

    public Book(String id, String name, Author author, Publisher publisher, String genre, int publicationYear, int pageNumber, Branch branch, boolean isAvailable, boolean isFav) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.pageNumber = pageNumber;
        this.branch = branch;
        this.isAvailable = isAvailable;
        this.isFav = isFav;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Book) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.publisher, that.publisher) &&
                Objects.equals(this.genre, that.genre) &&
                this.publicationYear == that.publicationYear &&
                this.pageNumber == that.pageNumber &&
                Objects.equals(this.branch, that.branch) &&
                this.isAvailable == that.isAvailable &&
                this.isFav == that.isFav;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publisher, genre, publicationYear, pageNumber, branch, isAvailable, isFav);
    }

    @Override
    public String toString() {
        return "tr.com.bookcell.book.Book[" +
                "idNumber=" + id + ", " +
                "name=" + name + ", " +
                "author=" + author + ", " +
                "publisher=" + publisher + ", " +
                "genre=" + genre + ", " +
                "publicationYear=" + publicationYear + ", " +
                "pageNumber=" + pageNumber + ", " +
                "branch=" + branch + ", " +
                "isAvailable=" + isAvailable + ", " +
                "isFav=" + isFav + ']';
    }

}

