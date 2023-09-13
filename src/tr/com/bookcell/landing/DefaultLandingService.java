package tr.com.bookcell.landing;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultLandingService implements LandingService {
    private final LandingRepository landingRepository;
    private final BookService bookService;
    private final CustomerService customerService;
    public DefaultLandingService(LandingRepository landingRepository, BookService bookService, CustomerService customerService) {
        this.landingRepository = landingRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    public void setPickUp(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            Landing landing = new Landing();
            landing.setCustomerId(customer.getId());
            landing.setBookId(book.getId());
            landing.setPickUpDate(LocalDate.now());
            bookService.setAvailable(book, false);
            landingRepository.setPickUp(landing);
        }

    }

    @Override
    public void setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        List<Landing> landings = getByCustomerAndBook(customer, book);
        LocalDate formattedPickUpDate = dateFormatter(pickUpDate);
        if (formattedPickUpDate == null) {
            System.out.println("Please enter the date according to the format (dd-mm-yyyy)");
        } else {
            if (landings != null) {
                for (Landing tempLanding : landings) {
                    if (tempLanding.getPickUpDate().equals(formattedPickUpDate)) {
                        bookService.setAvailable(book, true);
                        landingRepository.setDropOff(tempLanding, LocalDate.now());

                    }
                }
            }
            else{
                System.out.println("THERE IS NO SUCH THAT LANDING IN LANDINGS LIST");
        }
    }
}

    @Override
    public List<Landing> getByCustomerAndBook(Customer customer, Book book) {

        if (book != null && customer != null) {
            for (Landing temp : getAll()) {
                if (temp.getBookId().equals(book.getId()) && temp.getCustomerId().equals(customer.getId())) {
                    return landingRepository.getByCustomerAndBook(customer.getId(), book.getId());
                }
            }
        }
        return null;

    }

    @Override
    public List<Landing> getAll() {
        return landingRepository.getAll();
    }

}
