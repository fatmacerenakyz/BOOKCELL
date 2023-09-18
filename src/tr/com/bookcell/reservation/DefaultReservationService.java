package tr.com.bookcell.reservation;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.time.LocalDate;
import java.util.List;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

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
    public boolean add(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String deliveryDate) {
        LocalDate formattedStartDate = dateFormatter(startDate);
        LocalDate formattedDeliveryDate = dateFormatter(deliveryDate);
        if (formattedStartDate == null || formattedDeliveryDate == null || formattedStartDate.isAfter(formattedDeliveryDate) || formattedStartDate.isBefore(LocalDate.now()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println(ansiColorRed() + "PLEASE ENTER A PROPER DATE." + ansiColorReset());
            return false;
        }

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            List<Reservation> reservations = getByCustomer(customerEmail);
            Reservation temp = getByStartDate(customerEmail, bookName, authorName, authorSurname, startDate);
            Reservation reservation = new Reservation(customer.getId(), book.getId(), formattedStartDate, formattedDeliveryDate, false, false);
            if (temp != null) {
                System.out.println(ansiColorRed() + "YOU BOOKED THIS BOOK FOR THIS DATES ALREADY. " + ansiColorReset());
                return false;
            } else if (reservations != null) {
                for (Reservation tempReservation : reservations) {
                    if ((tempReservation.getStartDate().isBefore(formattedStartDate) && tempReservation.getDeliveryDate().isBefore(formattedStartDate)) || (tempReservation.getStartDate().isAfter(formattedDeliveryDate) && tempReservation.getDeliveryDate().isAfter(formattedDeliveryDate))) {
                        reservationRepository.add(reservation);
                        return true;
                    }

                }
            } else {
                reservationRepository.add(reservation);
                return true;
            }
            System.out.println(ansiColorRed() + "YOU HAVE ANOTHER RESERVATION FOR THIS DATES. " + ansiColorReset());
            return false;
        }
        return false;
    }

    @Override
    public boolean cancel(String customerEmail, String bookName, String authorName, String authorSurname, String startDate) {
        LocalDate formattedStartDate = dateFormatter(startDate);
        if (formattedStartDate == null) {
            System.out.println(ansiColorRed() + "PLEASE ENTER THE DATE ACCORDING TO THE FORMAT (DD-MM-YYYY)" + ansiColorReset());
            return false;
        }

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            for (Reservation tempReservation : getByCustomer(customerEmail)) {
                if (tempReservation.getBookId().equals(book.getId()) && tempReservation.getStartDate().equals(formattedStartDate)) {
                    reservationRepository.setCanceled(customer.getId(), book.getId(), formattedStartDate);
                    return true;
                }
            }
            System.out.println("THERE IS NO RESERVATION MADE FOR " + bookName + "  IN THIS CUSTOMER'S RESERVATION LIST.");
            return false;
        }
        return true;
    }

    @Override
    public boolean setStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String newStartDate, String deliveryDate) {
        LocalDate formattedStartDate = dateFormatter(newStartDate);
        LocalDate formattedDeliveryDate = dateFormatter(deliveryDate);
        if (formattedStartDate == null || formattedDeliveryDate == null) {
            System.out.println(ansiColorRed() + "PLEASE ENTER THE DATE ACCORDING TO THE FORMAT (DD-MM-YYYY)" + ansiColorReset());
            return false;
        }

        Reservation reservation = getByDeliveryDate(customerEmail, bookName, authorName, authorSurname, deliveryDate);
        if (reservation == null) {
            System.out.println(ansiColorRed() + "THERE IS NO RESERVATION MADE FOR " + bookName + " IN THIS CUSTOMER'S RESERVATION LIST." + ansiColorReset());
            return false;
        } else if (reservation.getStartDate().isAfter(reservation.getDeliveryDate()) || reservation.getStartDate().isBefore(LocalDate.now())) {
            System.out.println(ansiColorRed() + "PLEASE ENTER A VALID DATE" + ansiColorReset());
        } else {
            List<Reservation> reservations = getByCustomer(customerEmail);
            for (Reservation reservation1 : reservations) {
                if (reservation.equals(reservation1)) {
                    continue;
                } else if (!((reservation1.getStartDate().isBefore(formattedStartDate) && reservation1.getDeliveryDate().isBefore(formattedStartDate)) || (reservation1.getStartDate().isAfter(formattedDeliveryDate) && reservation1.getDeliveryDate().isAfter(formattedDeliveryDate)))) {
                    System.out.println(ansiColorRed() + "THERE IS ALREADY A RESERVATION IN THIS DATES" + ansiColorReset());
                    return false;
                }
            }
            Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
            Customer customer = customerService.getByEmail(customerEmail);
            if (book != null && customer != null) {
                reservationRepository.setStartDate(customer.getId(), book.getId(), formattedStartDate, formattedDeliveryDate);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate, String newDeliveryDate) {
        LocalDate formattedDeliveryDate = dateFormatter(newDeliveryDate);
        LocalDate formattedStartDate = dateFormatter(startDate);
        if (formattedDeliveryDate == null || formattedStartDate == null) {
            System.out.println(ansiColorRed() + "PLEASE ENTER THE DATE ACCORDING TO THE FORMAT (DD-MM-YYYY)" + ansiColorReset());
            return false;
        }

        Reservation reservation = getByStartDate(customerEmail, bookName, authorName, authorSurname, startDate);
        if (reservation == null) {
            System.out.println("THERE IS NO RESERVATION MADE FOR " + bookName + " IN THIS CUSTOMER'S RESERVATION LIST.");
            return false;
        } else if (formattedDeliveryDate.isBefore(reservation.getStartDate()) || formattedDeliveryDate.isBefore(LocalDate.now())) {
            System.out.println(ansiColorRed() + "PLEASE ENTER A VALID DATE" + ansiColorReset());
        } else {
            List<Reservation> reservations = getByCustomer(customerEmail);
            for (Reservation reservation1 : reservations) {
                if (reservation.equals(reservation1)) {
                    continue;
                } else if (!((reservation1.getStartDate().isBefore(formattedStartDate) && reservation1.getDeliveryDate().isBefore(formattedStartDate)) || (reservation1.getStartDate().isAfter(formattedDeliveryDate) && reservation1.getDeliveryDate().isAfter(formattedDeliveryDate)))) {
                    System.out.println(ansiColorRed() + "THERE IS ALREADY A RESERVATION IN THIS DATES!" + ansiColorReset());
                    return false;
                }
            }

            Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
            Customer customer = customerService.getByEmail(customerEmail);
            if (book != null && customer != null) {
                reservationRepository.setDeliveryDate(customer.getId(), book.getId(), formattedStartDate, formattedDeliveryDate);
                return true;
            }


        }
        return false;
    }

    @Override
    public void setPickedUp(Integer reservationId, boolean isPickedUp) {
        reservationRepository.setPickedUp(reservationId, isPickedUp);
    }

    @Override
    public Reservation getByStartDate(String customerEmail, String bookName, String authorName, String authorSurname, String startDate) {
        LocalDate formattedStartDate = dateFormatter(startDate);
        if (formattedStartDate == null) {
            System.out.println(ansiColorRed() + "PLEASE ENTER THE DATE ACCORDING TO THE FORMAT (DD-MM-YYYY)" + ansiColorReset());
            return null;
        } else {

            Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
            Customer customer = customerService.getByEmail(customerEmail);

            if (book != null && customer != null) {
                for (Reservation tempReservation : getByCustomer(customerEmail)) {
                    if (tempReservation.getBookId().equals(book.getId()) && tempReservation.getStartDate().equals(formattedStartDate)) {
                        return reservationRepository.getByStartDate(customer.getId(), book.getId(), formattedStartDate);
                    }
                }
            }
        }
        return null;
    }

    public Reservation getByDeliveryDate(String customerEmail, String bookName, String authorName, String authorSurname, String deliveryDate) {
        LocalDate formattedDeliveryDate = dateFormatter(deliveryDate);
        if (formattedDeliveryDate == null) {
            System.out.println(ansiColorRed() + "PLEASE ENTER THE DATE ACCORDING TO THE FORMAT (DD-MM-YYYY)" + ansiColorReset());
            return null;
        } else {

            Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
            Customer customer = customerService.getByEmail(customerEmail);

            if (book != null && customer != null) {
                for (Reservation tempReservation : getByCustomer(customerEmail)) {
                    if (tempReservation.getBookId().equals(book.getId()) && tempReservation.getDeliveryDate().equals(formattedDeliveryDate)) {
                        return reservationRepository.getByDeliveryDate(customer.getId(), book.getId(), formattedDeliveryDate);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getByCustomer(String customerEmail) {
        Customer customer = customerService.getByEmail(customerEmail);
        return reservationRepository.getByCustomer(customer.getId());
    }

    @Override
    public List<Reservation> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname) {

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            for (Reservation temp : getByCustomer(customerEmail)) {
                if (temp.getBookId().equals(book.getId())) {
                    return reservationRepository.getByCustomerAndBook(customer.getId(), book.getId());
                }
            }
        }
        return null;
    }

}
