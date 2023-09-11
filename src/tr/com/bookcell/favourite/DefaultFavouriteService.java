package tr.com.bookcell.favourite;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultFavouriteService implements FavouriteService {
    private final FavouriteRepository favouriteRepository;

    public DefaultFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void add(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Book book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
        if (book != null) {
            boolean bool = false;
            for (Favourite tempFavourite : getByCustomerEmail(customer.getEmail())) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    System.out.println("THERE IS ALREADY " + formattedBookName + " IN FAVOURITES LIST");
                    bool = true;
                    break;
                }
            }
            if(!bool){
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
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
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
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
        return favouriteRepository.getByCustomerId(customer.getId());
    }
}
