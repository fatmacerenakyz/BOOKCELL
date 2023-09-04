package tr.com.bookcell.reservation;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private Integer id;
    private Integer customerId;
    private Integer bookId;
    private LocalDate startDate;
    private LocalDate deliveryDate;


    public Reservation() {
    }

    public Reservation(Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
    }

    public Reservation(Integer id, Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(bookId, that.bookId) && Objects.equals(startDate, that.startDate) && Objects.equals(deliveryDate, that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, bookId, startDate, deliveryDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", bookId=" + bookId +
                ", startDate=" + startDate +
                ", expiryDate=" + deliveryDate +
                '}';
    }
}
