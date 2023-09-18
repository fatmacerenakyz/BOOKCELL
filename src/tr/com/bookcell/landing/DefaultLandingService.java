package tr.com.bookcell.landing;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.reservation.Reservation;
import tr.com.bookcell.reservation.ReservationService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.time.LocalDate;
import java.util.List;

import static tr.com.bookcell.util.DateFormatter.dateFormatter;
import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

public class DefaultLandingService implements LandingService {
    private final LandingRepository landingRepository;
    private final BookService bookService;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    public DefaultLandingService(LandingRepository landingRepository, BookService bookService, CustomerService customerService, ReservationService reservationService) {
        this.landingRepository = landingRepository;
        this.bookService = bookService;
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    @Override
    public boolean setPickUp(String customerEmail, String bookName, String authorName, String authorSurname) {

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            if(!(book.isAvailable())){
                System.out.println(ansiColorRed()+"THIS BOOK IS NOT AVAILABLE. "+ansiColorReset());
                return false;
            }
            else {
                List<Reservation> reservations = reservationService.getByCustomerAndBook(customerEmail, bookName, authorName, authorSurname);
                if(reservations==null){
                    System.out.println(ansiColorRed()+"YOU HAVE NO RESERVATIONS!"+ansiColorReset());
                    return false;
                }
                List<Landing> landings = getByCustomerAndBook(customerEmail, bookName, authorName, authorSurname);
                if(landings!=null) {
                    for (Landing tempLanding : landings) {
                        if (tempLanding.getDropOffDate() == null) {
                            System.out.println(ansiColorRed() + "YOU HAVE ALREADY BORROW THIS BOOK. PLEASE DELIVER IT FIRST!!!" + ansiColorReset());
                            return false;
                        }
                    }
                }
                for (Reservation tempReservation : reservations) {
                    if (!(tempReservation.isCanceled()) && !(tempReservation.isPickedUp())) {
                        if (tempReservation.getStartDate().isBefore(LocalDate.now())) {
                            System.out.println(ansiColorRed() + "YOUR PICK UP DATE HAS EXPIRED. YOUR RESERVATION HAS BEEN CANCELED.");
                            tempReservation.setCanceled(true);
                            return false;
                        } else if (tempReservation.getStartDate().isAfter(LocalDate.now())) {
                            System.out.println(ansiColorRed() + "YOUR PICK UP DATE HAS NOT COME YET." + ansiColorReset());
                            return false;
                        } else {
                            reservationService.setPickedUp(tempReservation.getId(), true);
                        }
                    }
                }
                Landing landing = new Landing();
                landing.setCustomerId(customer.getId());
                landing.setBookId(book.getId());
                landing.setPickUpDate(LocalDate.now());
                bookService.setAvailable(book.getId(), false);
                landingRepository.setPickUp(landing);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean setDropOff(String customerEmail, String bookName, String authorName, String authorSurname, String pickUpDate) {

        LocalDate formattedPickUpDate = dateFormatter(pickUpDate);
        if (formattedPickUpDate == null) {
            System.out.println(ansiColorRed()+"PLEASE ENTER THE DATE ACCORDING TO FORMAT (DD-MM-YYYY)"+ansiColorReset());
            return false;
        }
        List<Reservation> reservations = reservationService.getByCustomerAndBook(customerEmail, bookName, authorName, authorSurname);
        List<Landing> landings = getByCustomerAndBook(customerEmail, bookName, authorName, authorSurname);
        for(Reservation tempReservation : reservations){
            if(!(tempReservation.isCanceled()) && (tempReservation.isPickedUp())){
                if (tempReservation.getDeliveryDate().isBefore(LocalDate.now())) {
                    System.out.println(ansiColorRed() + "YOUR DELIVERY DATE HAS NOT COME YET");
                    return false;
                } else if (tempReservation.getDeliveryDate().isAfter(LocalDate.now())) {
                    System.out.println(ansiColorRed() + "YOUR DELIVERY DATE HAS EXPIRED. YOUR RESERVATION HAS BEEN CANCELED." + ansiColorReset());
                    reservationService.cancel(customerEmail, bookName, authorName, authorSurname, pickUpDate);
                    return false;
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
                }
            }
        }

        System.out.println(ansiColorRed()+"THERE IS NO SUCH THAT LANDING IN LANDINGS LIST"+ansiColorReset());
        return false;
    }

    @Override
    public List<Landing> getByCustomerAndBook(String customerEmail, String bookName, String authorName, String authorSurname) {

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
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
    public List<Landing> getByCustomer(String customerEmail) {
        Customer customer = customerService.getByEmail(customerEmail);
        return landingRepository.getByCustomer(customer.getId());
    }

    @Override
    public List<Landing> getAll() {
        return landingRepository.getAll();
    }

}
