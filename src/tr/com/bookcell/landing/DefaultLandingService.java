package tr.com.bookcell.landing;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.time.LocalDate;
import java.util.List;

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
    public boolean setPickUp(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedCustomerEmail = lowerCaseForEmail(customerEmail);
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = customerService.getByEmail(formattedCustomerEmail);
        if (book != null && customer != null) {
            List<Landing> landings = getByCustomerAndBook(formattedCustomerEmail, formattedBookName, formattedAuthorName, formattedAuthorSurname);
            for (Landing tempLanding : landings) {
                if (tempLanding.getDropOffDate() == null) {
                    System.out.println("You have already borrowed this book. Please deliver the book first.");
                    return false;
                }
            }
            Landing landing = new Landing();
            landing.setCustomerId(customer.getId());
            landing.setBookId(book.getId());
            landing.setPickUpDate(LocalDate.now());
            bookService.setAvailable(book.getId(), false);
            landingRepository.setPickUp(landing);

        }
        return true;
    }

    @Override
    public boolean setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate) {
        String formattedCustomerEmail = lowerCaseForEmail(customerEmail);
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        List<Landing> landings = getByCustomerAndBook(formattedCustomerEmail, formattedBookName, formattedAuthorName, formattedAuthorSurname);
        LocalDate formattedPickUpDate = dateFormatter(pickUpDate);
        if (formattedPickUpDate == null) {
            System.out.println("Please enter the date according to the format (dd-mm-yyyy)");
        } else {
            if (landings != null) {
                for (Landing tempLanding : landings) {
                    if (tempLanding.getPickUpDate().equals(formattedPickUpDate)) {
                        bookService.setAvailable(tempLanding.getBookId(), true);
                        landingRepository.setDropOff(tempLanding, LocalDate.now());
                        return true;
                    }
                }
            }
            System.out.println("THERE IS NO SUCH THAT LANDING IN LANDINGS LIST");
        }
        return true;
    }

    @Override
    public List<Landing> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedCustomerEmail = lowerCaseForEmail(customerEmail);
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = customerService.getByEmail(formattedCustomerEmail);
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
