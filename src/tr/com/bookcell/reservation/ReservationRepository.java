package tr.com.bookcell.reservation;

import tr.com.bookcell.BaseRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends BaseRepository {
    void add(Reservation reservation);
    void remove(Integer customerId, Integer bookId);
    void setStartDate(Integer customerId, Integer bookId, LocalDate startDate);
    void setDeliveryDate(Integer customerId, Integer bookId, LocalDate deliveryDate);
    Reservation getByCustomerAndBook(Integer customerId, Integer bookId);
    List<Reservation> getByCustomerId(Integer customerId);
}
