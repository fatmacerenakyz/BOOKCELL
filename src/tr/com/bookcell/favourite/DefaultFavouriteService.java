package tr.com.bookcell.favourite;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultFavouriteService implements FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    public DefaultFavouriteService(FavouriteRepository favouriteRepository, BookService bookService, CustomerService customerService) {
        this.favouriteRepository = favouriteRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    public void add(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);


        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null) {
            boolean bool = false;
            for (Favourite tempFavourite : getByCustomerEmail(customer.getEmail())) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    System.out.println("THERE IS ALREADY " + formattedBookName + " IN FAVOURITES LIST");
                    bool = true;
                    break;
                }
            }
            if (!bool) {
                Favourite favourite = new Favourite(customer.getId(), book.getId());
                favouriteRepository.add(favourite);
            }
        }
    }

    @Override
    public void remove(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        Book book = bookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);

        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null) {
            for (Favourite tempFavourite : getByCustomerEmail(customer.getEmail())) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    favouriteRepository.remove(customer.getId(), book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO " + formattedBookName + " IN FAVOURITES LIST!");
        }

    }

    @Override
    public List<Favourite> getByCustomerEmail(String customerEmail) {

        Customer customer = customerService.getByEmail(customerEmail);
        return favouriteRepository.getByCustomerId(customer.getId());
    }
}
