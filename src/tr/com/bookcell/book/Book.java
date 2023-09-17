package tr.com.bookcell.book;

import java.util.Objects;

public class Book {
    private Integer id;
    private String name;
    private Integer authorId;
    private Integer publisherId;
    private String genre;
    private int publicationYear;
    private int pageNumber;
    private boolean isAvailable;

    public Book() {
    }

    public Book(String name, Integer authorId, boolean isAvailable) {
        this.name = name;
        this.authorId = authorId;
        this.isAvailable = isAvailable;
    }

    public Book(String name, Integer authorId) {
        this.name = name;
        this.authorId = authorId;
    }

    public Book(String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber, boolean isAvailable) {
        this.name = name;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.pageNumber = pageNumber;
        this.isAvailable = isAvailable;
    }

    public Book(String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber) {
        this.name = name;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.pageNumber = pageNumber;
    }

    public Book(Integer id, String name, Integer authorId, Integer publisherId, String genre, int publicationYear, int pageNumber, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.pageNumber = pageNumber;
        this.isAvailable = isAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return publicationYear == book.publicationYear && pageNumber == book.pageNumber && isAvailable == book.isAvailable && Objects.equals(id, book.id) && Objects.equals(name, book.name) && Objects.equals(authorId, book.authorId) && Objects.equals(publisherId, book.publisherId) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorId, publisherId, genre, publicationYear, pageNumber, isAvailable);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", publisherId=" + publisherId +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                ", pageNumber=" + pageNumber +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

