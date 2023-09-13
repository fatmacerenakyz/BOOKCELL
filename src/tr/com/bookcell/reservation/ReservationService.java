package tr.com.bookcell.reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    void add(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate);
    void remove(String customerEmail, String bookName, String authorName, String authorSurname, String startDate);
    void setStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String newStartDate, String deliveryDate);
    void setDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String newDeliveryDate);
    Reservation getByStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate);
    Reservation getByDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String deliveryDate);
    List<Reservation> getByCustomer(String customerEmail);
}
