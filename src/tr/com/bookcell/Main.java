package tr.com.bookcell;

import tr.com.bookcell.author.*;
import tr.com.bookcell.book.*;
import tr.com.bookcell.favourite.DefaultFavouriteRepository;
import tr.com.bookcell.favourite.DefaultFavouriteService;
import tr.com.bookcell.favourite.FavouriteRepository;
import tr.com.bookcell.favourite.FavouriteService;
import tr.com.bookcell.landing.*;
import tr.com.bookcell.publisher.DefaultPublisherRepository;
import tr.com.bookcell.publisher.DefaultPublisherService;
import tr.com.bookcell.publisher.PublisherRepository;
import tr.com.bookcell.publisher.PublisherService;
import tr.com.bookcell.reservation.DefaultReservationRepository;
import tr.com.bookcell.reservation.DefaultReservationService;
import tr.com.bookcell.reservation.ReservationRepository;
import tr.com.bookcell.reservation.ReservationService;
import tr.com.bookcell.user.customer.CustomerRepository;
import tr.com.bookcell.user.customer.CustomerService;
import tr.com.bookcell.user.customer.DefaultCustomerRepository;
import tr.com.bookcell.user.customer.DefaultCustomerService;

import java.time.LocalDate;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);

        AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
        AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);

        PublisherRepository defaultPublisherRepository = new DefaultPublisherRepository();
        PublisherService defaultPublisherService = new DefaultPublisherService(defaultPublisherRepository);

        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);

        FavouriteRepository defaultFavouriteRepository = new DefaultFavouriteRepository();
        FavouriteService defaultFavouriteService = new DefaultFavouriteService(defaultFavouriteRepository);

        ReservationRepository defaultReservationRepository = new DefaultReservationRepository();
        ReservationService defaultReservationService = new DefaultReservationService(defaultReservationRepository);

        LandingRepository defaultLandingRepository = new DefaultLandingRepository();
        LandingService defaultLandingService = new DefaultLandingService(defaultLandingRepository);
        /*



        //defaultAuthorService.add("Agatha", "Christine");
        for (Author author : defaultAuthorService.getByName("Stephen")) {
            System.out.println(author);
        }

        defaultBookService.add("Franny ve Zoey", 1, 2, "Uzun Hikaye", 1961, 155, true);


        //defaultPublisherService.add("Altın Yayıncılık");
        //defaultPublisherService.add("inkılap yayınevi");
        //defaultPublisherService.remove("Altın Yayıncılık");
        //defaultPublisherService.remove("inkılap yayınevi");


        //defaultAuthorService.add("David", "Salinger");
        //defaultAuthorService.add("Reşat Nuri", "Güntekin");
        //defaultAuthorService.add("Sait Faik", "Abasıyanık");
        //defaultAuthorService.add("Emile", "Zola");
        for (Author author : defaultAuthorService.getAll()){
            System.out.println(author);
        }
        System.out.println("\n");
        defaultAuthorService.remove("Emile", "Zola");
        for (Author author : defaultAuthorService.getAll()){
            System.out.println(author);
        }
        defaultBookService.remove("Franny ve Zoey", 5);
        for(Book book : defaultBookService.getAll()){
            System.out.println(book);
        }
        for(Book book : defaultBookService.getAll()){
            System.out.println(book);
        }
        defaultBookService.setAvailable("Franny ve Zoey", 5, false);
        for(Book book : defaultBookService.getAll()){
            System.out.println(book);
        }
        */
        //defaultPublisherService.add("YKY");
        //defaultCustomerService.add("ceren@gmail.com", "ceren12345", "ceren", "akyuz");
        //System.out.println(defaultAuthorService.getByNameAndSurname("David", "Salinger"));
        //defaultBookService.remove("Franny ve Zoey", "David", "Salinger");
        //defaultBookService.add("Franny ve Zoey", 5, 8, "Uzun Hikaye", 1961, 155, true);
        //defaultBookService.add("Billy Summers", 1, 7, "Polisiye", 2021, 528, true);
        //defaultFavouriteService.add(8, "Billy Summers", "Stephen", "King");
        //defaultFavouriteService.remove(8,"Billy Summers", "Stephen", "King");
        //defaultReservationService.add(7, "Franny ve Zoey", "David", "Salinger", "04-09-2023", "05-10-2023");
        //defaultReservationService.remove(8, "Billy Summers", "Stephen", "King");
        //defaultReservationService.setDeliveryDate(7, "Franny ve Zoey", "David", "Salinger", "10-10-2023");
        //defaultReservationService.add(7, "Billy Summers", "Stephen", "King", "08-09-2023", "11-09-2023");
        //defaultReservationService.setDeliveryDate(7, "Billy Summers", "Stephen", "King","07-09-2023");
        //defaultLandingService.pickUp(8, "Billy Summers", "Stephen", "King");
        //defaultLandingService.pickUp(8, "Billy Summers", "Stephen", "King");
        //defaultLandingService.dropOff(8, "Billy Summers", "Stephen", "King", "05-09-2023");


    }
}
