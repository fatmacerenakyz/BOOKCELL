package tr.com.bookcell.reservation;

import java.util.List;

public interface ReservationService {
    boolean add(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate);

    boolean cancel(String customerEmail, String bookName, String authorName, String authorSurname, String startDate);

    boolean setStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String newStartDate, String deliveryDate);

    boolean setDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String newDeliveryDate);
    void setPickedUp(Integer reservationId, boolean isPickedUp);

    Reservation getByStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate);

    Reservation getByDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String deliveryDate);

    List<Reservation> getByCustomer(String customerEmail);
    List<Reservation> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname);

}
