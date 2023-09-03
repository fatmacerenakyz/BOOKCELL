package tr.com.bookcell.favourite;

import java.util.Objects;

public class Favourite {
    private Integer id;
    private Integer customerId;
    private Integer bookId;

    public Favourite() {
    }

    public Favourite(Integer customerId, Integer bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public Favourite(Integer id, Integer customerId, Integer bookId) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Favourite favourite)) return false;
        return Objects.equals(id, favourite.id) && Objects.equals(customerId, favourite.customerId) && Objects.equals(bookId, favourite.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, bookId);
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id='" + id + '\'' +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                '}';
    }
}
