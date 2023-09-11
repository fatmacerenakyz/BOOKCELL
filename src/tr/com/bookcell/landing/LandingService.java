package tr.com.bookcell.landing;

public interface LandingService {
    void setPickUp(String customerEmail, String bookName, String authorName, String authorSurname);
    void setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate);
}
