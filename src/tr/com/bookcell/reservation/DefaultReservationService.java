package tr.com.bookcell.reservation;

import tr.com.bookcell.book.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultReservationService implements ReservationService {
    private final ReservationRepository reservationRepository;


    public DefaultReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        DateTimeFormatter formatter = dateFormatter();
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        LocalDate formattedDeliveryDate = LocalDate.parse(deliveryDate, formatter);
        if (formattedStartDate.isAfter(formattedDeliveryDate) || formattedStartDate.isBefore(LocalDate.now()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println("Please enter a proper date.");
        } else {
            BookRepository defaultBookRepository = new DefaultBookRepository();
            BookService defaultBookService = new DefaultBookService(defaultBookRepository);
            Book book = new Book();
            book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
            if (book != null) {
                boolean bool = false;
                for (Reservation tempReservation : getByCustomer(customerId)) {
                    if (tempReservation.getBookId().equals(book.getId())) {
                        System.out.println("THERE WAS ALREADY MADE A RESERVATION WITH" + book.getName());
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    Reservation reservation = new Reservation(customerId, book.getId(), formattedStartDate, formattedDeliveryDate);
                    reservationRepository.add(reservation);
                }
            }
        }
    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            for (Reservation tempReservation : getByCustomer(customerId)) {
                if (tempReservation.getBookId().equals(book.getId())) {
                    reservationRepository.remove(customerId, book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + "  IN THIS CUSTOMER'S RESERVATION LIST.");
        }
    }

    @Override
    public void setStartDate(Integer customerId, String bookName, String authorName, String authorSurname, String startDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        DateTimeFormatter formatter = dateFormatter();
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        Reservation reservation = new Reservation();
        reservation = getByCustomerAndBook(customerId, formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (reservation != null && formattedStartDate.isAfter(reservation.getDeliveryDate()) || formattedStartDate.isBefore(LocalDate.now())) {
            System.out.println("Please enter a valid date");
        } else {
            BookRepository defaultBookRepository = new DefaultBookRepository();
            BookService defaultBookService = new DefaultBookService(defaultBookRepository);
            Book book = new Book();
            book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
            if (book != null) {
                reservationRepository.setStartDate(customerId, book.getId(), formattedStartDate);
            }
        }
    }

    @Override
    public void setDeliveryDate(Integer customerId, String bookName, String authorName, String authorSurname, String deliveryDate) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        DateTimeFormatter formatter = dateFormatter();
        LocalDate formattedDeliveryDate = LocalDate.parse(deliveryDate, formatter);
        Reservation reservation = new Reservation();
        reservation = getByCustomerAndBook(customerId, formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (reservation == null) {
            System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + " IN THIS CUSTOMER'S RESERVATION LIST.");
        } else if (formattedDeliveryDate.isBefore(reservation.getStartDate()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println("Please enter a valid date");
        } else {
            BookRepository defaultBookRepository = new DefaultBookRepository();
            BookService defaultBookService = new DefaultBookService(defaultBookRepository);
            Book book = new Book();
            book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
            if (book != null) {
                reservationRepository.setDeliveryDate(customerId, book.getId(), formattedDeliveryDate);
            }
        }
    }

    @Override
    public Reservation getByCustomerAndBook(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            for (Reservation tempReservation : getByCustomer(customerId)) {
                if (tempReservation.getBookId().equals(book.getId())) {
                    return reservationRepository.getByCustomerAndBook(customerId, book.getId());
                }
            }
            System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + "  IN THIS CUSTOMER'S RESERVATION LIST.");
        }
        return null;
    }

    @Override
    public List<Reservation> getByCustomer(Integer customerId) {
        return reservationRepository.getByCustomer(customerId);
    }
}
