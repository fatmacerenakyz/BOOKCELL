package tr.com.bookcell.reservation;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.time.LocalDate;
import java.util.List;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultReservationService implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    public DefaultReservationService(ReservationRepository reservationRepository, BookService bookService, CustomerService customerService) {
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    public void add(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate) {

        LocalDate formattedStartDate = dateFormatter(startDate);
        LocalDate formattedDeliveryDate = dateFormatter(deliveryDate);
        if (formattedStartDate == null || formattedDeliveryDate == null) {
            System.out.println("Please enter the date according to the format (dd-mm-yyyy)");
        } else {
            if (formattedStartDate.isAfter(formattedDeliveryDate) || formattedStartDate.isBefore(LocalDate.now()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
                System.out.println("Please enter a proper date.");
            } else {
                String formattedBookName = capitalizeForBookName(bookName);
                String formattedAuthorName = capitalizeForMultipleStrings(authorName);
                String formattedAuthorSurname = capitalizeFirst(authorSurname);

                Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
                if (book != null) {
                    boolean bool = false;
                    List<Reservation> reservations = getByCustomerEmail(customerEmail);
                    Reservation temp = getByCustomerAndBook(customerEmail, bookName, authorName, authorSurname);
                    for (Reservation tempReservation : reservations) {
                        if (tempReservation.getStartDate().equals(formattedStartDate) && tempReservation.getDeliveryDate().equals(formattedDeliveryDate)) {
                            if (temp != null) {
                                System.out.println("There is already a reservation with the same customer, book, and date");
                            } else {
                                System.out.println("You have already a reservation in between this dates!");
                            }
                            bool = true;
                            break;
                        } else if (tempReservation.getStartDate().isBefore(formattedStartDate) && tempReservation.getDeliveryDate().isAfter(formattedDeliveryDate)) {
                            System.out.println("You have another reservation for these dates.");
                            bool = true;
                            break;
                        } else if (formattedStartDate.isBefore(tempReservation.getDeliveryDate())) {
                            if (temp != null) {
                                System.out.println("You have already a resevation with this book in this days.");
                            } else {
                                System.out.println("You have already a resevation in this days.");
                            }
                            bool = true;
                            break;
                        }
                    }
                    if (!bool) {

                        Customer customer = customerService.getByEmail(customerEmail);
                        Reservation reservation = new Reservation(customer.getId(), book.getId(), formattedStartDate, formattedDeliveryDate);
                        reservationRepository.add(reservation);
                    }
                }
            }
        }


    }

    @Override
    public void remove(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            for (Reservation tempReservation : getByCustomerEmail(customerEmail)) {
                if (tempReservation.getBookId().equals(book.getId())) {

                    Customer customer = customerService.getByEmail(customerEmail);
                    reservationRepository.remove(customer.getId(), book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + "  IN THIS CUSTOMER'S RESERVATION LIST.");
        }
    }

    @Override
    public void setStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate) {
        LocalDate formattedStartDate = dateFormatter(startDate);

        if (formattedStartDate == null) {
            System.out.println("Please enter the date according to the format (dd-mm-yyyy)");
        } else {
            String formattedBookName = capitalizeForBookName(bookName);
            String formattedAuthorName = capitalizeForMultipleStrings(authorName);
            String formattedAuthorSurname = capitalizeFirst(authorSurname);
            Reservation reservation = getByCustomerAndBook(customerEmail, formattedBookName, formattedAuthorName, formattedAuthorSurname);
            if (reservation == null) {
                System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + " IN THIS CUSTOMER'S RESERVATION LIST.");
            } else if (formattedStartDate.isAfter(reservation.getDeliveryDate()) || formattedStartDate.isBefore(LocalDate.now())) {
                System.out.println("Please enter a valid date");
            } else {
                boolean bool = false;
                List<Reservation> reservations = getByCustomerEmail(customerEmail);
                for (Reservation reservation1 : reservations) {
                    if (reservation1.getDeliveryDate().isAfter(formattedStartDate)) {
                        System.out.println("There is already a reservation in this dates.");
                        bool = true;
                        break;
                    }
                }
                if (!bool) {

                    Book book = new Book();
                    book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
                    if (book != null) {

                        Customer customer = customerService.getByEmail(customerEmail);
                        reservationRepository.setStartDate(customer.getId(), book.getId(), formattedStartDate);
                    }
                }
            }
        }
    }

    @Override
    public void setDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String deliveryDate) {

        LocalDate formattedDeliveryDate = dateFormatter(deliveryDate);
        if (formattedDeliveryDate == null) {
            System.out.println("Please enter the date according to the format (dd-mm-yyyy)");
        } else {
            String formattedBookName = capitalizeForBookName(bookName);
            String formattedAuthorName = capitalizeForMultipleStrings(authorName);
            String formattedAuthorSurname = capitalizeFirst(authorSurname);
            Reservation reservation = getByCustomerAndBook(customerEmail, formattedBookName, formattedAuthorName, formattedAuthorSurname);
            if (reservation == null) {
                System.out.println("THERE IS NO RESERVATION MADE FOR " + formattedBookName + " IN THIS CUSTOMER'S RESERVATION LIST.");
            } else if (formattedDeliveryDate.isBefore(reservation.getStartDate()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
                System.out.println("Please enter a valid date");
            } else {
                boolean bool = false;
                List<Reservation> reservations = getByCustomerEmail(customerEmail);
                for (Reservation reservation1 : reservations) {
                    if (reservation1.getStartDate().isBefore(formattedDeliveryDate)) {
                        System.out.println("There is already a reservation in this dates.");
                        bool = true;
                        break;
                    }
                }
                if (!bool) {

                    Book book = new Book();
                    book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
                    if (book != null) {

                        Customer customer = customerService.getByEmail(customerEmail);
                        reservationRepository.setDeliveryDate(customer.getId(), book.getId(), formattedDeliveryDate);
                    }
                }
            }
        }
    }

    @Override
    public Reservation getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            for (Reservation tempReservation : getByCustomerEmail(customerEmail)) {
                if (tempReservation.getBookId().equals(book.getId())) {

                    Customer customer = customerService.getByEmail(customerEmail);
                    return reservationRepository.getByCustomerAndBook(customer.getId(), book.getId());
                }
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getByCustomerEmail(String customerEmail) {

        Customer customer = customerService.getByEmail(customerEmail);
        return reservationRepository.getByCustomerId(customer.getId());
    }
}
