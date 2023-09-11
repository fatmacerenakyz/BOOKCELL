package tr.com.bookcell.reservation;

import java.util.List;

public interface ReservationService {
    void add(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate);
    void remove(String customerEmail, String bookName, String authorName, String authorSurname);
    void setStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate);
    void setDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String deliveryDate);
    Reservation getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Reservation> getByCustomerEmail(String customerEmail);
}
