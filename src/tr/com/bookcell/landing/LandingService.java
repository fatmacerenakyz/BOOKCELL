package tr.com.bookcell.landing;
import java.util.List;

public interface LandingService {
    boolean setPickUp(String customerEmail, String bookName, String authorName, String authorSurname);

    boolean setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate);

    List<Landing> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Landing> getByCustomer(String customerEmail);

    List<Landing> getAll();
}
