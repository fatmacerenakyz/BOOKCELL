package tr.com.bookcell.landing;

import tr.com.bookcell.BaseRepository;

import java.sql.Date;
import java.time.LocalDate;

public interface LandingRepository extends BaseRepository {
    void setPickUp(Landing landing);
    void setDropOff(Integer customerId, Integer bookId, LocalDate dropOffDate, Date pickUpDate);
}
