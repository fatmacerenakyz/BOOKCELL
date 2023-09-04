package tr.com.bookcell.reservation;

import tr.com.bookcell.book.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DefaultReservationService implements ReservationService {
    private final ReservationRepository reservationRepository;
    BookRepository defaultBookRepository = new DefaultBookRepository();
    BookService defaultBookService = new DefaultBookService(defaultBookRepository);
    Book book = new Book();
    Reservation reservation = new Reservation();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");


    public DefaultReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate) {
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        LocalDate formattedDeliveryDate = LocalDate.parse(deliveryDate, formatter);
        if (formattedStartDate.isAfter(formattedDeliveryDate)) {
            System.out.println("The delivery date cannot be earlier than the start date.");
        }
        if (formattedStartDate.isBefore(LocalDate.now()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println("Reservation dates cannot be earlier than the current date.");
        } else {
            book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
            Reservation reservation = new Reservation(customerId, book.getId(), formattedStartDate, formattedDeliveryDate);
            reservationRepository.add(reservation);
        }

    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        reservationRepository.remove(customerId, book.getId());
    }

    @Override
    public void setStartDate(Integer customerId, String bookName, String authorName, String authorSurname, String startDate) {
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        reservation = getByCustomerAndBook(customerId, bookName, authorName, authorSurname);
        if (formattedStartDate.isAfter(reservation.getDeliveryDate())) {
            System.out.println("The delivery date cannot be earlier than the start date.");
        }
        if (formattedStartDate.isBefore(LocalDate.now())) {
            System.out.println("Start date cannot be earlier than the current date.");
        } else {
            reservationRepository.setStartDate(customerId, book.getId(), formattedStartDate);
        }
    }

    @Override
    public void setDeliveryDate(Integer customerId, String bookName, String authorName, String authorSurname, String deliveryDate) {
        LocalDate formattedDeliveryDate = LocalDate.parse(deliveryDate, formatter);
        reservation = getByCustomerAndBook(customerId, bookName, authorName, authorSurname);
        if (formattedDeliveryDate.isBefore(reservation.getStartDate())) {
            System.out.println("The delivery date cannot be earlier than the start date.");
        }
        if (formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println("Delivery date cannot be earlier than the current date.");
        } else {
            reservationRepository.setDeliveryDate(customerId, book.getId(), formattedDeliveryDate);
        }
    }

    @Override
    public Reservation getByCustomerAndBook(Integer customerId, String bookName, String authorName, String authorSurname) {
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        return reservationRepository.getByCustomerAndBook(customerId, book.getId());
    }
}
