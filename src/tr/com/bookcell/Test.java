package tr.com.bookcell;

import tr.com.bookcell.author.AuthorRepository;
import tr.com.bookcell.author.AuthorService;
import tr.com.bookcell.author.DefaultAuthorRepository;
import tr.com.bookcell.author.DefaultAuthorService;
import tr.com.bookcell.basket.*;
import tr.com.bookcell.book.*;
import tr.com.bookcell.favourite.*;
import tr.com.bookcell.landing.*;
import tr.com.bookcell.publisher.*;
import tr.com.bookcell.reservation.*;
import tr.com.bookcell.user.admin.AdminRepository;
import tr.com.bookcell.user.admin.AdminService;
import tr.com.bookcell.user.admin.DefaultAdminRepository;
import tr.com.bookcell.user.admin.DefaultAdminService;
import tr.com.bookcell.user.customer.*;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.WeakHashMap;

import static tr.com.bookcell.util.TestClassMethods.*;

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
        LandingService defaultLandingService = new DefaultLandingService(defaultLandingRepository, defaultBookService, defaultCustomerService, defaultReservationService);

        AdminRepository defaultAdminRepository = new DefaultAdminRepository();
        AdminService defaultAdminService = new DefaultAdminService(defaultAdminRepository);

        BasketRepository defaultBasketRepository = new DefaultBasketRepository();
        BasketService defaultBasketService = new DefaultBasketService(defaultBasketRepository, defaultBookService, defaultCustomerService);
        Scanner scanner = new Scanner(System.in);

        String customerEmail = "";
        String customerPassword = "";
        String customerName = "";
        String customerSurname = "";
        String adminUsername = "";
        String adminPassword = "";

        while (true) {
            System.out.println(ansiColorBold() + ansiColorItalic() + ansiColorDarkBlue() + "\t\t\t\t\t\tWELCOME TO BOOKCELL DIGITAL LIBRARY SYSTEM" + ansiColorReset());
            String userType = "";
            boolean isAdmin;
            boolean backToUserType;
            do {
                backToUserType = false;
                System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "PLEASE ENTER YOUR USER TYPE (CUSTOMER/ADMIN): " + ansiColorReset());
                userType = scanner.nextLine();
                if (userType.equalsIgnoreCase("customer")) {
                    String isNewCustomer = "";
                    do {
                        System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "ARE YOU A NEW CUSTOMER? (YES/NO) " + ansiColorReset());
                        isNewCustomer = scanner.nextLine();
                    } while (!(isNewCustomer.equalsIgnoreCase("yes") || isNewCustomer.equalsIgnoreCase("no")));
                    Customer tempCustomer = null;
                    boolean newCustomer = true;
                    if (isNewCustomer.equalsIgnoreCase("YES")) {
                        boolean isCustomerAdded;
                        do {
                            System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "PLEASE ENTER THE REQUIRED INFORMATION FOR REGISTERING." + ansiColorReset());
                            System.out.println("Email:  ");
                            customerEmail = scanner.nextLine();
                            tempCustomer = defaultCustomerService.getByEmail(customerEmail);
                            if (tempCustomer != null) {
                                System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU HAVE ALREADY REGISTERED. YOU'RE DIRECTED TO THE SIGN IN PAGE!!" + ansiColorReset());
                                break;
                            }
                            System.out.println("Name:  ");
                            customerName = scanner.nextLine();
                            System.out.println("Surname:  ");
                            customerSurname = scanner.nextLine();
                            System.out.println("Password (least 8 chars/Aa/.,_/123): ");
                            customerPassword = scanner.nextLine();
                            isCustomerAdded = defaultCustomerService.add(customerEmail, customerPassword, customerName, customerSurname);
                        } while (!isCustomerAdded);
                    }
                    if (tempCustomer != null || isNewCustomer.equalsIgnoreCase("NO")) {
                        do {
                            if (!newCustomer) {
                                System.out.println(ansiColorRed() + "YOUR PASSWORD IS NOT CORRECT." + ansiColorReset());
                                System.out.println("Password:  ");
                                customerPassword = scanner.nextLine();
                            } else {
                                boolean tryAgain;
                                do {
                                    tryAgain = false;
                                    System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "WELCOME BACK!! PLEASE ENTER YOUR EMAIL AND PASSWORD." + ansiColorReset());
                                    System.out.println("Email:  ");
                                    customerEmail = scanner.nextLine();
                                    System.out.println("Password:  ");
                                    customerPassword = scanner.nextLine();
                                    tempCustomer = defaultCustomerService.getByEmail(customerEmail);
                                    if (tempCustomer != null) {
                                        newCustomer = false;
                                    } else {
                                        boolean correnctAnswer;
                                        do {
                                            System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "YOUR EMAIL IS NOT FOUND. WOULD YOU TRY AGAIN (again) OR GO BACK TO SIGN UP PAGE (back)??" + ansiColorReset());
                                            String answer = scanner.nextLine();
                                            answer = answer.toLowerCase(Locale.ENGLISH);
                                            switch (answer) {
                                                case ("again") -> {
                                                    tryAgain = true;
                                                    correnctAnswer = true;
                                                }
                                                case ("back") -> {
                                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE SIGN UP PAGE." + ansiColorReset());
                                                    correnctAnswer = true;
                                                }
                                                default -> {
                                                    System.out.println(ansiColorRed() + "PLEASE ENTER \"again\" or \"back\"" + ansiColorReset());
                                                    correnctAnswer = false;
                                                }
                                            }
                                        } while (!correnctAnswer);
                                    }
                                } while (tryAgain);
                            }
                        } while (tempCustomer != null && !defaultCustomerService.isPasswordCorrect(customerEmail, customerPassword));

                        if (tempCustomer != null) {
                            Customer customer = defaultCustomerService.getByEmail(customerEmail);
                            customerName = customer.getName();
                            customerSurname = customer.getSurname();
                        }
                    }
                } else if (userType.equalsIgnoreCase("admin")) {

                    do {
                        isAdmin = true;
                        backToUserType = false;
                        System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "ENTER YOUR USERNAME AND PASSWORD. " + ansiColorReset());
                        System.out.println("Username: ");
                        adminUsername = scanner.nextLine();
                        System.out.println("Password: ");
                        adminPassword = scanner.nextLine();
                        if (!(defaultAdminService.get().getUserName().equalsIgnoreCase(adminUsername) && defaultAdminService.get().getPassword().equalsIgnoreCase(adminPassword))) {
                            System.out.println(ansiColorRed() + "YOU'RE NOT OUR ADMIN!!!!" + ansiColorReset());
                            isAdmin = false;
                            System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "ENTER YOUR INFORMATIONS AGAIN (again) OR GO BACK TO USER TYPE QUESTION? (back)" + ansiColorReset());
                            String answer = scanner.nextLine();
                            answer = answer.toLowerCase();
                            switch (answer) {
                                case ("again") -> {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "TRY AGAIN." + ansiColorReset());
                                }
                                case ("back") -> {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU'RE REDIRECTED TO THE USER TYPE QUESTION." + ansiColorReset());
                                    backToUserType = true;
                                }
                                default ->
                                        System.out.println(ansiColorRed() + "ENTER again OR back!" + ansiColorReset());
                            }
                        }
                    } while (!isAdmin && !backToUserType);
                }
            } while ((backToUserType) || (!(userType.equalsIgnoreCase("customer") || userType.equalsIgnoreCase("admin"))));

            boolean yesOrNoAnswer = true;
            boolean isBackToMenu = true;
            String backToMenuInput = "";
            boolean chooseOption;
            do {
                if(userType.equalsIgnoreCase("customer")) {
                    chooseOption = true;
                    System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "HELLO " + customerName + " " + customerSurname + " PLEASE ENTER A INPUT APPROPRIATE TO YOUR REQUEST." + ansiColorReset());
                    System.out.println(ansiColorCyan() + "1 for SEARCHING A BOOK.");
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
                    System.out.println("12 for SEEING MY LANDINGS. ");
                    System.out.println("13 for BACK TO SIGN IN/UP PAGE. ");
                    System.out.println("14 for exit" + ansiColorReset());
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
                                System.out.println(ansiColorRed() + "THERE IS NO " + bookName + " IN BOOKS LIST!" + ansiColorReset());
                                isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                            } else {
                                Publisher publisher = defaultPublisherService.getById(book.getPublisherId());
                                System.out.println(ansiColorMagenta() + "Name: " + ansiColorReset() + bookName.toUpperCase(Locale.ENGLISH) + ansiColorMagenta() + " Author: " + ansiColorReset() + authorName.toUpperCase(Locale.ENGLISH) + " " + authorSurname.toUpperCase(Locale.ENGLISH) + ansiColorMagenta() + " Publisher: " + ansiColorReset() + publisher.getName() + ansiColorMagenta() + " Genre: " + ansiColorReset() + book.getGenre() + ansiColorMagenta() + " Publication Year: " + ansiColorReset() + book.getPublicationYear() + ansiColorMagenta() + " Page Number: " + ansiColorReset() + book.getPageNumber());
                            }
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("2") -> {
                            boolean isFavouriteAdded;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                isFavouriteAdded = defaultFavouriteService.add(customerEmail, bookName, authorName, authorSurname);
                                if (!isFavouriteAdded) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE BOOK HAS BEEN CORRECTLY ADDED TO YOUR FAVORITES." + ansiColorReset());
                                }
                            } while (!isFavouriteAdded && !isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("3") -> {
                            boolean isBasketAdded;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                isBasketAdded = defaultBasketService.add(customerEmail, bookName, authorName, authorSurname);
                                if (!isBasketAdded) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE BOOK HAS BEEN CORRECTLY ADDED TO YOUR BASKET." + ansiColorReset());
                                }
                            } while (!isBasketAdded);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("4") -> {
                            boolean isReserved;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                System.out.println("Book Purchase Date (DD-MM-YYYY): ");
                                String reservationStartDate = scanner.nextLine();
                                System.out.println("Book Release Date (DD-MM-YYYY): ");
                                String reservationDeliveryDate = scanner.nextLine();
                                isReserved = defaultReservationService.add(customerEmail, bookName, authorName, authorSurname, reservationStartDate, reservationDeliveryDate);
                                if (!isReserved) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE BOOK HAS BEEN CORRECTLY ADDED TO YOUR RESERVATIONS." + ansiColorReset());
                                }
                            } while (!isReserved && !isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("5") -> {
                            boolean isReservationCanceled;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                System.out.println("Reservation Start Date (DD-MM-YYYY): ");
                                String startDate = scanner.nextLine();
                                isReservationCanceled = defaultReservationService.cancel(customerEmail, bookName, authorName, authorSurname, startDate);
                                if (!isReservationCanceled) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOUR RESERVATION HAS BEEN SUCCESSFULLY CANCELED." + ansiColorReset());
                                }
                            } while (!isReservationCanceled && !isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("6") -> {
                            boolean isReservationStartDateSet = true;
                            boolean isReservationReleaseDateSet = true;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                System.out.println("Reservation Start Date (DD-MM-YYYY): ");
                                String startDate = scanner.nextLine();
                                System.out.println("Reservation Expiry Date (DD-MM-YYYY): ");
                                String deliveryDate = scanner.nextLine();
                                boolean isPurchaseOrRelease;
                                do {
                                    isPurchaseOrRelease = true;
                                    System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "WHICH ONE DO YOU WANT TO SET: purchase date (enter P)/release date (enter R)? " + ansiColorReset());
                                    String setReservationOption = scanner.nextLine();
                                    setReservationOption = setReservationOption.toUpperCase();
                                    switch (setReservationOption) {
                                        case ("P") -> {
                                            System.out.println("New Purchase Date: ");
                                            String newStartDate = scanner.nextLine();
                                            isReservationStartDateSet = defaultReservationService.setStartDate(customerEmail, bookName, authorName, authorSurname, newStartDate, deliveryDate);
                                            if (!isReservationStartDateSet) {
                                                isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                            } else {
                                                System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "NEW PURCHASE DATE HAS BEEN SET SUCCESSFULLY.");
                                            }
                                        }
                                        case ("R") -> {
                                            System.out.println("New Release Date: ");
                                            String newReleaseDate = scanner.nextLine();
                                            isReservationReleaseDateSet = defaultReservationService.setDeliveryDate(customerEmail, bookName, authorName, authorSurname, startDate, newReleaseDate);
                                            if (!isReservationReleaseDateSet) {
                                                isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                            } else {
                                                System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "NEW RELEASE DATE HAS BEEN SET SUCCESSFULLY.");
                                            }
                                        }
                                        default -> {
                                            System.out.println(ansiColorRed() + "ENTER P or R" + ansiColorReset());
                                            isPurchaseOrRelease = false;
                                        }
                                    }
                                } while (!isPurchaseOrRelease);
                            } while ((!isReservationStartDateSet || !isReservationReleaseDateSet) && !isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("7") -> {
                            boolean isLanded;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                isLanded = defaultLandingService.setPickUp(customerEmail, bookName, authorName, authorSurname);
                                if (!isLanded) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOUR LANDING HAS BEEN CREATED SUCCESSFULLY" + ansiColorReset());
                                }
                            } while (!isLanded && !isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("8") -> {
                            boolean isDropped;
                            do {
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                System.out.println("Book Landing Date (DD-MM-YYYY): ");
                                String pickUpDate = scanner.nextLine();
                                isDropped = defaultLandingService.setDropOff(customerEmail, bookName, authorName, authorSurname, pickUpDate);
                                if (!isDropped) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOUR DROPPING HAS BEEN CREATED SUCCESSFULLY" + ansiColorReset());
                                }
                            } while (!isDropped && isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("9") -> {
                            List<Reservation> reservations = defaultReservationService.getByCustomer(customerEmail);
                            if (reservations.isEmpty()) {
                                System.out.println(ansiColorRed() + "YOU DON'T HAVE ANY RESERVATIONS!" + ansiColorReset());
                            } else {
                                for(Reservation temp : reservations){
                                    Book book = defaultBookService.getById(temp.getBookId());
                                    System.out.println(ansiColorMagenta()+"BOOK NAME: "+ansiColorReset()+book.getName()+ansiColorMagenta()+" START DATE: "+ansiColorReset()+temp.getStartDate()+ansiColorMagenta()+" DELIVERY DATE: "+ansiColorReset()+temp.getDeliveryDate());
                                }
                            }
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("10") -> {
                            List<Favourite> favourites = defaultFavouriteService.getByCustomer(customerEmail);
                            if (favourites.isEmpty()) {
                                System.out.println(ansiColorRed() + "YOU DON'T HAVE ANY FAVOURITES!" + ansiColorReset());
                            } else {
                                for(Favourite temp : favourites){
                                    Book book = defaultBookService.getById(temp.getBookId());
                                    System.out.println(ansiColorMagenta()+"BOOK NAME: "+ansiColorReset()+book.getName());
                                }
                            }
                            System.out.println(ansiColorGreenBackGround() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("11") -> {
                            List<Basket> baskets = defaultBasketService.getByCustomer(customerEmail);
                            if (baskets.isEmpty()) {
                                System.out.println(ansiColorRed() + "YOU DON'T HAVE ANY FAVOURITES!" + ansiColorReset());
                            } else {
                                for(Basket temp : baskets){
                                    Book book = defaultBookService.getById(temp.getBookId());
                                    System.out.println(ansiColorMagenta()+"BOOK NAME: "+ansiColorReset()+book.getName());
                                }
                            }
                            System.out.println(ansiColorGreenBackGround() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("12") -> {
                            List<Landing> landings = defaultLandingService.getByCustomer(customerEmail);
                            if (landings.isEmpty()) {
                                System.out.println(ansiColorRed() + "YOU DON'T HAVE ANY LANDINGS!" + ansiColorReset());
                            } else {
                                for(Landing temp : landings){
                                    Book book = defaultBookService.getById(temp.getBookId());
                                    System.out.println(ansiColorMagenta()+ "BOOK NAME: "+ansiColorReset()+book.getName()+ansiColorMagenta()+ " PICK UP DATE: "+ansiColorReset()+temp.getPickUpDate()+ansiColorMagenta()+" DROP OFF DATE: "+ansiColorReset()+temp.getDropOffDate());
                                }
                            }
                            System.out.println(ansiColorGreenBackGround() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }
                        case ("13") -> chooseOption = false;
                        case ("14") -> {
                            System.out.println(ansiColorMagentaBackGround() + ansiColorBlack() + "THE SESSION IS ENDING." + ansiColorReset());
                            return;
                        }
                        default -> {
                            System.out.println(ansiColorRed() + "PLEASE CHOOSE ON OF THE OPTIONS IN THE MENU!!" + ansiColorReset());
                        }
                    }
                }
                else{
                    chooseOption = true;
                    System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "HELLO " + adminUsername + " PLEASE ENTER A INPUT APPROPRIATE TO YOUR REQUEST." + ansiColorReset());
                    System.out.println(ansiColorCyan() + "1 for ADDING BOOK.");
                    System.out.println("2 for ADDING AUTHOR.");
                    System.out.println("3 for ADDING PUBLISHER.");
                    System.out.println("4 for exit" + ansiColorReset());
                    String option = scanner.nextLine();
                    switch (option) {
                        case("1") -> {
                            boolean isAdded;
                            do{
                                System.out.println("Book Name:  ");
                                String bookName = scanner.nextLine();
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                System.out.println("Publisher Name: ");
                                String publisherName = scanner.nextLine();
                                System.out.println("Genre: ");
                                String genre = scanner.nextLine();
                                System.out.println("Publication Year: ");
                                Integer publicationYear = scanner.nextInt();
                                System.out.println("Page Number: ");
                                Integer pageNumber = scanner.nextInt();
                                isAdded = defaultBookService.add(bookName, authorName, authorSurname, publisherName, genre, publicationYear, pageNumber);
                                if (!isAdded) {
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE BOOK HAS BEEN SUCCESSFULLY ADDED TO THE DATABASE. " + ansiColorReset());
                                }
                            } while (!isAdded && isBackToMenu);
                            System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                        }case("2") -> {
                            boolean isAdded;
                            do{
                                System.out.println("Author Name:  ");
                                String authorName = scanner.nextLine();
                                System.out.println("Author Surname:  ");
                                String authorSurname = scanner.nextLine();
                                isAdded = defaultAuthorService.add(authorName, authorSurname);
                                if(!isAdded){
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE AUTHOR HAS BEEN SUCCESSFULLY ADDED TO THE DATABASE. " + ansiColorReset());
                                }

                            }while (!isAdded&&!isBackToMenu);
                        }case("3") -> {
                            boolean isAdded;
                            do{
                                System.out.println("Publisher Name: ");
                                String publisherName = scanner.nextLine();
                                isAdded = defaultPublisherService.add(publisherName);
                                if(!isAdded){
                                    isBackToMenu = backToMenuWhile(backToMenuInput, isBackToMenu, yesOrNoAnswer, scanner);
                                } else {
                                    System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "THE PUBLISHER HAS BEEN SUCCESSFULLY ADDED TO THE DATABASE. " + ansiColorReset());
                                }
                            }while (!isAdded&&!isBackToMenu);

                        }case("4") -> {
                            System.out.println(ansiColorMagentaBackGround() + ansiColorBlack() + "THE SESSION IS ENDING." + ansiColorReset());
                            return;
                        }
                        default -> System.out.println(ansiColorRed() + "PLEASE CHOOSE ON OF THE OPTIONS IN THE MENU!!" + ansiColorReset());
                    }
                }
            } while (chooseOption);

        }

    }
}
