package tr.com.bookcell;

import tr.com.bookcell.author.AuthorRepository;
import tr.com.bookcell.author.AuthorService;
import tr.com.bookcell.author.DefaultAuthorRepository;
import tr.com.bookcell.author.DefaultAuthorService;
import tr.com.bookcell.basket.BasketRepository;
import tr.com.bookcell.basket.BasketService;
import tr.com.bookcell.basket.DefaultBasketRepository;
import tr.com.bookcell.basket.DefaultBasketService;
import tr.com.bookcell.book.*;
import tr.com.bookcell.favourite.DefaultFavouriteRepository;
import tr.com.bookcell.favourite.DefaultFavouriteService;
import tr.com.bookcell.favourite.FavouriteRepository;
import tr.com.bookcell.favourite.FavouriteService;
import tr.com.bookcell.landing.DefaultLandingRepository;
import tr.com.bookcell.landing.DefaultLandingService;
import tr.com.bookcell.landing.LandingRepository;
import tr.com.bookcell.landing.LandingService;
import tr.com.bookcell.publisher.DefaultPublisherRepository;
import tr.com.bookcell.publisher.DefaultPublisherService;
import tr.com.bookcell.publisher.PublisherRepository;
import tr.com.bookcell.publisher.PublisherService;
import tr.com.bookcell.reservation.DefaultReservationRepository;
import tr.com.bookcell.reservation.DefaultReservationService;
import tr.com.bookcell.reservation.ReservationRepository;
import tr.com.bookcell.reservation.ReservationService;
import tr.com.bookcell.user.admin.AdminRepository;
import tr.com.bookcell.user.admin.AdminService;
import tr.com.bookcell.user.admin.DefaultAdminRepository;
import tr.com.bookcell.user.admin.DefaultAdminService;
import tr.com.bookcell.user.customer.*;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        AuthorRepository defaultAuthorRepository = new DefaultAuthorRepository();
        AuthorService defaultAuthorService = new DefaultAuthorService(defaultAuthorRepository);

        PublisherRepository defaultPublisherRepository = new DefaultPublisherRepository();
        PublisherService defaultPublisherService = new DefaultPublisherService(defaultPublisherRepository);

        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository, defaultAuthorService, defaultPublisherService);

        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);

        FavouriteRepository defaultFavouriteRepository = new DefaultFavouriteRepository();
        FavouriteService defaultFavouriteService = new DefaultFavouriteService(defaultFavouriteRepository, defaultBookService, defaultCustomerService);

        ReservationRepository defaultReservationRepository = new DefaultReservationRepository();
        ReservationService defaultReservationService = new DefaultReservationService(defaultReservationRepository, defaultBookService, defaultCustomerService);

        LandingRepository defaultLandingRepository = new DefaultLandingRepository();
        LandingService defaultLandingService = new DefaultLandingService(defaultLandingRepository, defaultBookService, defaultCustomerService);

        AdminRepository defaultAdminRepository = new DefaultAdminRepository();
        AdminService defaultAdminService = new DefaultAdminService(defaultAdminRepository);

        BasketRepository defaultBasketRepository = new DefaultBasketRepository();
        BasketService defaultBasketService = new DefaultBasketService(defaultBasketRepository, defaultBookService, defaultCustomerService);
        Scanner scanner = new Scanner(System.in);
        //TEXT COLORS
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_DARKBLUE = "\u001B[34m";
        String ANSI_MAGENTA = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        //BACKGROUND COLORS
        String ANSI_RED_BG = "\u001B[41m";
        String ANSI_GREEN_BG = "\u001B[42m";
        String ANSI_YELLOW_BG = "\u001B[43m";
        String ANSI_BLUE_BG = "\u001B[44m";
        String ANSI_MAGENTA_BG = "\u001B[45m";
        String ANSI_CYAN_BG = "\u001B[46m";
        //TEXT STYLES
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_ITALIC = "\u001B[3m";
        String ANSI_HIDDEN = "\u001B[8m";
        String customerEmail = "";
        String customerPassword = "";
        String customerName = "";
        String customerSurname = "";
        String adminUsername = "";
        String adminPassword = "";
        while (true) {
            System.out.println(ANSI_BOLD + ANSI_DARKBLUE + "\t\t\t\t\t\tWELCOME TO BOOKCELL DIGITAL LIBRARY SYSTEM" + ANSI_RESET);
            String userType = "";
            do {
                System.out.println(ANSI_GREEN + "PLEASE ENTER YOUR USER TYPE (CUSTOMER/ADMIN): " + ANSI_RESET);
                userType = scanner.nextLine();
            } while (!(userType.equalsIgnoreCase("customer") || userType.equalsIgnoreCase("admin")));
            if (userType.equalsIgnoreCase("customer")) {
                String isNewCustomer = "";
                do {
                    System.out.println(ANSI_GREEN + "ARE YOU A NEW CUSTOMER? (YES/NO) " + ANSI_RESET);
                    isNewCustomer = scanner.nextLine();
                } while (!(isNewCustomer.equalsIgnoreCase("yes") || isNewCustomer.equalsIgnoreCase("no")));
                Customer tempCustomer = null;
                if (isNewCustomer.equalsIgnoreCase("YES")) {
                    boolean isCustomerAdded = true;
                    do {
                        System.out.println("PLEASE ENTER THE REQUIRED INFORMATION FOR REGISTERING.");
                        System.out.println("Name:  ");
                        customerName = scanner.nextLine();
                        System.out.println("Surname:  ");
                        customerSurname = scanner.nextLine();
                        System.out.println("Email:  ");
                        customerEmail = scanner.nextLine();
                        System.out.println("Password (least 8 chars/Aa/.,_/123): ");
                        customerPassword = scanner.nextLine();
                        tempCustomer = defaultCustomerService.getByEmail(customerEmail);
                        isCustomerAdded = defaultCustomerService.add(customerEmail, customerPassword, customerName, customerSurname);
                    } while (tempCustomer == null && !isCustomerAdded);
                }
                if (tempCustomer != null || isNewCustomer.equalsIgnoreCase("NO")) {
                    do {
                        System.out.println("PLEASE ENTER CORRECT EMAIL AND PASSWORD!");
                        System.out.println("Email:  ");
                        customerEmail = scanner.nextLine();
                        System.out.println("Password:  ");
                        customerPassword = scanner.nextLine();
                        tempCustomer = defaultCustomerService.getByEmail(customerEmail);
                    } while (tempCustomer != null && !defaultCustomerService.isPasswordCorrect(customerEmail, customerPassword));

                    Customer customer = defaultCustomerService.getByEmail(customerEmail);
                    customerName = customer.getName();
                    customerSurname = customer.getSurname();

                }
                while (true) {
                    System.out.println("HELLO " + customerName + " " + customerSurname + " PLEASE ENTER A INPUT APPROPRIATE TO YOUR REQUEST.");
                    System.out.println("1 for SEARCHING A BOOK.");
                    System.out.println("2 for ADDING BOOKS TO FAVOURITE.");
                    System.out.println("3 for ADDING BOOKS TO BASKET.");
                    System.out.println("4 for RESERVING A BOOK.");
                    System.out.println("5 for CANCELING A RESERVATION.");
                    System.out.println("6 for SETTING RESERVATION DETAILS.");
                    System.out.println("7 for LANDING A BOOK.");
                    System.out.println("8 for RETURNING A BOOK.");
                    System.out.println("9 for SEEING MY RESERVATIONS.");
                    System.out.println("10 for SEEING MY FAVOURITES.");
                    System.out.println("11 for SEEING MY BASKET.");
                    System.out.println("12 for exit");
                    String option = scanner.nextLine();
                    switch (option) {
                        case ("1") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            Book book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
                            if (book == null) {
                                System.out.println("THERE IS NO " + bookName + " IN BOOKS LIST!");
                                continue;
                            } else {
                                System.out.println(book);
                            }
                        }
                        case ("2") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            boolean isFavouriteAdded = defaultFavouriteService.add(customerEmail, bookName, authorName, authorSurname);
                            if (!isFavouriteAdded) continue;
                        }
                        case ("3") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            boolean isBasketAdded = defaultBasketService.add(customerEmail, bookName, authorName, authorSurname);
                            if (!isBasketAdded) continue;
                        }
                        case ("4") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            System.out.println("Book Purchase Date (DD/MM/YYYY): ");
                            String reservationStartDate = scanner.nextLine();
                            System.out.println("Book Release Date (DD/MM/YYYY): ");
                            String reservationDeliveryDate = scanner.nextLine();
                            boolean isReserved = defaultReservationService.add(customerEmail, bookName, authorName, authorSurname, reservationStartDate, reservationDeliveryDate);
                            if (!isReserved) continue;
                        }
                        case ("5") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            System.out.println("Reservation Start Date (DD/MM/YYYY): ");
                            String startDate = scanner.nextLine();
                            boolean isReservationCanceled = defaultReservationService.remove(customerEmail, bookName, authorName, authorSurname, startDate);
                            if (!isReservationCanceled) continue;
                        }
                        case ("6") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            System.out.println("Reservation Start Date (DD/MM/YYYY): ");
                            String startDate = scanner.nextLine();
                            System.out.println("Reservation Expiry Date (DD/MM/YYYY): ");
                            String deliveryDate = scanner.nextLine();
                            System.out.println("WHICH ONE DO YOU WANT TO SET: purchase date (enter P)/release date (enter R)? ");
                            String setReservationOption = scanner.nextLine();
                            switch (setReservationOption) {
                                case ("P") -> {
                                    System.out.println("New Purchase Date: ");
                                    String newStartDate = scanner.nextLine();
                                    boolean isReservationStartDateSet = defaultReservationService.setStartDate(customerEmail, bookName, authorName, authorSurname, newStartDate, deliveryDate);
                                    if (!isReservationStartDateSet) continue;
                                }
                                case ("R") -> {
                                    System.out.println("New Release Date: ");
                                    String newReleaseDate = scanner.nextLine();
                                    boolean isReservationReleaseDateSet = defaultReservationService.setDeliveryDate(customerEmail, bookName, authorName, authorSurname, startDate, newReleaseDate);
                                    if (!isReservationReleaseDateSet) continue;
                                }
                                default -> System.out.println("ENTER P or R");
                            }
                        }
                        case ("7") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            boolean isLanded = defaultLandingService.setPickUp(customerEmail, bookName, authorName, authorSurname);
                            if (!isLanded) continue;
                        }
                        case ("8") -> {
                            System.out.println("Book Name:  ");
                            String bookName = scanner.nextLine();
                            System.out.println("Author Name:  ");
                            String authorName = scanner.nextLine();
                            System.out.println("Author Surname:  ");
                            String authorSurname = scanner.nextLine();
                            System.out.println("Book Landing Date (DD/MM/YYYY): ");
                            String pickUpDate = scanner.nextLine();
                            boolean isDropped = defaultLandingService.setDropOff(customerEmail, bookName, authorName, authorSurname, pickUpDate);
                            if (!isDropped) continue;
                        }
                        case ("9") -> System.out.println(defaultReservationService.getByCustomer(customerEmail));
                        case ("10") -> System.out.println(defaultFavouriteService.getByCustomer(customerEmail));
                        case ("11") -> System.out.println(defaultBasketService.getByCustomer(customerEmail));
                        case ("12") -> {
                            return;
                        }
                        default -> System.out.println("PLEASE CHOOSE ON OF THE OPTIONS IN THE MENU!!");
                    }
                }
            } else {
                System.out.println("PLEASE ENTER YOUR USERNAME AND PASSWORD.");
                System.out.println("username: ");
                adminUsername = scanner.nextLine();
                System.out.println("password: ");
                adminPassword = scanner.nextLine();
            }
        }
    }
}
