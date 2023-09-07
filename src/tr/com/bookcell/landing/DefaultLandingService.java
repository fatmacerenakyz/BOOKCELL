package tr.com.bookcell.landing;

import tr.com.bookcell.book.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;
import static tr.com.bookcell.util.InputFormatter.capitalizeForBookName;

public class DefaultLandingService implements LandingService {
    private final LandingRepository landingRepository;

    public DefaultLandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    @Override
    public void setPickUp(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = new Book();
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            Landing landing = new Landing();
            landing.setCustomerId(customerId);
            landing.setBookId(book.getId());
            landing.setPickUpDate(LocalDate.now());
            landingRepository.setPickUp(landing);
        }
    }

    @Override
    public void setDropOff(Integer customerId, String bookName, String authorName, String authorSurname, String pickUpDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = new Book();
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            DateTimeFormatter formatter = dateFormatter();
            LocalDate formattedPickUpDate = LocalDate.parse(pickUpDate, formatter);
            landingRepository.setDropOff(customerId, book.getId(), LocalDate.now(), Date.valueOf(formattedPickUpDate));
        }

    }

}
