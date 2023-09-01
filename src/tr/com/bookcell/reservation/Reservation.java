package tr.com.bookcell.reservation;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.user.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Reservation {
    private Integer id;
    private Integer customerId;
    private Integer bookId;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;


    public Reservation() {
    }

    public Reservation(Integer id, Integer customerId, Integer bookId, LocalDateTime startDate, LocalDateTime expiryDate) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(bookId, that.bookId) && Objects.equals(startDate, that.startDate) && Objects.equals(expiryDate, that.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, bookId, startDate, expiryDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
