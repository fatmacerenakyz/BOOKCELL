package tr.com.bookcell.landing;

public interface LandingService {
    void pickUp(Integer customerId, String bookName, String authorName, String authorSurname);
    void dropOff(Integer customerId, String bookName, String authorName, String authorSurname, String pickUpDate);
}
