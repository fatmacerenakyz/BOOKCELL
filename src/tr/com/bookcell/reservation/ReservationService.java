package tr.com.bookcell.reservation;

public interface ReservationService {
    void add(Integer customerId, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate);
    void remove(Integer customerId, String bookName, String authorName, String authorSurname);
    void setStartDate(Integer customerId, String bookName, String authorName, String authorSurname, String startDate);
    void setDeliveryDate(Integer customerId, String bookName, String authorName, String authorSurname, String deliveryDate);
    Reservation getByCustomerAndBook(Integer customerId, String bookName, String authorName, String authorSurname);
}
