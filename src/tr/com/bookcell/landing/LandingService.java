package tr.com.bookcell.landing;

public interface LandingService {
    void setPickUp(Integer customerId, String bookName, String authorName, String authorSurname);
    void setDropOff(Integer customerId, String bookName, String authorName, String authorSurname, String pickUpDate);
}
