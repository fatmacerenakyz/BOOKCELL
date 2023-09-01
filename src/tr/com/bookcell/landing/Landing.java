package tr.com.bookcell.landing;

import java.time.LocalDateTime;
import java.util.Objects;

public class Landing {
    private Integer id;
    private Integer customerId;
    private Integer bookId;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;

    public Landing() {

    }

    public Landing(Integer id, Integer customerId, Integer bookId, LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
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

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Landing landing)) return false;
        return Objects.equals(id, landing.id) && Objects.equals(customerId, landing.customerId) && Objects.equals(bookId, landing.bookId) && Objects.equals(pickUpDate, landing.pickUpDate) && Objects.equals(dropOffDate, landing.dropOffDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, bookId, pickUpDate, dropOffDate);
    }

    @Override
    public String toString() {
        return "Landing{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                ", pickUpDate=" + pickUpDate +
                ", dropOffDate=" + dropOffDate +
                '}';
    }
}
