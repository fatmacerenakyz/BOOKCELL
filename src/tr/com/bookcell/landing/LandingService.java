package tr.com.bookcell.landing;

import java.util.List;

public interface LandingService {
    void setPickUp(String customerEmail, String bookName, String authorName, String authorSurname);
    void setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate);
    List<Landing> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Landing> getAll();
}
