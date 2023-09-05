package tr.com.bookcell.landing;

import tr.com.bookcell.book.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;

public class DefaultLandingService implements LandingService {
    private final LandingRepository landingRepository;

    public DefaultLandingService(LandingRepository landingRepository) {
        this.landingRepository = landingRepository;
    }

    @Override
    public void pickUp(Integer customerId, String bookName, String authorName, String authorSurname) {
        Book book = new Book();
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);

        Landing landing = new Landing();
        landing.setCustomerId(customerId);
        landing.setBookId(book.getId());
        landing.setPickUpDate(LocalDate.now());
        landingRepository.pickUp(landing);

    }

    @Override
    public void dropOff(Integer customerId, String bookName, String authorName, String authorSurname, String pickUpDate) {
        Book book = new Book();
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        DateTimeFormatter formatter = dateFormatter();
        LocalDate formattedPickUpDate = LocalDate.parse(pickUpDate, formatter);
        landingRepository.dropOff(customerId, book.getId(), LocalDate.now(), formattedPickUpDate);
    }

}
