package tr.com.bookcell.landing;

import tr.com.bookcell.BaseRepository;

import java.sql.Date;
import java.time.LocalDate;

public interface LandingRepository extends BaseRepository {
    void pickUp(Landing landing);
    void dropOff(Integer customerId, Integer bookId, LocalDate dropOffDate, LocalDate pickUpDate);
}
