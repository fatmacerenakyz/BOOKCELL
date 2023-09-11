package tr.com.bookcell.landing;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultLandingService implements LandingService {
    private final LandingRepository landingRepository;

    public DefaultLandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    @Override
    public void setPickUp(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
        if (book != null) {
            Landing landing = new Landing();
            landing.setCustomerId(customer.getId());
            landing.setBookId(book.getId());
            landing.setPickUpDate(LocalDate.now());
            landingRepository.setPickUp(landing);
        }
    }

    @Override
    public void setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
        if (book != null) {
            DateTimeFormatter formatter = dateFormatter();
            LocalDate formattedPickUpDate = LocalDate.parse(pickUpDate, formatter);
            landingRepository.setDropOff(customer.getId(), book.getId(), LocalDate.now(), formattedPickUpDate);
        }

    }

}
