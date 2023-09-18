package tr.com.bookcell.favourite;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.util.List;
import java.util.Locale;

import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

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
    public boolean add(String customerEmail, String bookName, String authorName, String authorSurname) {

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            for (Favourite tempFavourite : getByCustomer(customer.getEmail())) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    System.out.println(ansiColorRed()+"THERE IS ALREADY " + bookName + " IN FAVOURITES LIST"+ansiColorReset());
                    return false;
                }
            }
            Favourite favourite = new Favourite(customer.getId(), book.getId());
            favouriteRepository.add(favourite);
            return true;
        }
        System.out.println(ansiColorRed()+"THERE IS NO "+bookName+" IN BOOKS LIST!"+ansiColorReset());
        return false;
    }

    @Override
    public void remove(String customerEmail, String bookName, String authorName, String authorSurname) {

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null) {
            for (Favourite tempFavourite : getByCustomer(customer.getEmail())) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    favouriteRepository.remove(customer.getId(), book.getId());
                    return;
                }
            }
            System.out.println(ansiColorRed()+"THERE IS NO " + bookName + " IN FAVOURITES LIST!"+ansiColorReset());
        }

    }

    @Override
    public List<Favourite> getByCustomer(String customerEmail) {
        Customer customer = customerService.getByEmail(customerEmail);
        return favouriteRepository.getByCustomer(customer.getId());
    }
}
