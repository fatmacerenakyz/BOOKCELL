package tr.com.bookcell.reservation;

import tr.com.bookcell.BaseRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends BaseRepository {
    void add(Reservation reservation);
    void setCanceled(Integer customerId, Integer bookId, LocalDate startDate);
    void setStartDate(Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate);
    void setDeliveryDate(Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate);
    void setPickedUp(Integer reservationId, boolean isPickedUp);
    Reservation getByStartDate(Integer customerId, Integer bookId, LocalDate startDate);
    Reservation getByDeliveryDate(Integer customerId, Integer bookId, LocalDate deliveryDate);
    List<Reservation> getByCustomer(Integer customerId);
    List<Reservation> getByCustomerAndBook(Integer customerId, Integer bookId);
}
