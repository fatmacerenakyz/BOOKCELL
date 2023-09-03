package tr.com.bookcell.basket;

import java.util.Objects;

public class Basket {
    private Integer id;
    private Integer customerId;
    private Integer bookId;

    public Basket() {
    }

    public Basket(Integer customerId, Integer bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public Basket(Integer id, Integer customerId, Integer bookId) {
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
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket basket)) return false;
        return Objects.equals(id, basket.id) && Objects.equals(customerId, basket.customerId) && Objects.equals(bookId, basket.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, bookId);
    }
}
