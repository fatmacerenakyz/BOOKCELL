package tr.com.bookcell.landing;

import tr.com.bookcell.BaseRepository;

import java.time.LocalDate;
import java.util.List;

public interface LandingRepository extends BaseRepository {
    void setPickUp(Landing landing);
    void setDropOff(Landing landing, LocalDate dropOffDate);
    List<Landing> getByCustomerAndBook(Integer customerId, Integer bookId);
    List<Landing> getByCustomer(Integer customerId);
    List<Landing> getAll();
}
