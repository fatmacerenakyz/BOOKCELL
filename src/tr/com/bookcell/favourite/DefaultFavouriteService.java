package tr.com.bookcell.favourite;

import tr.com.bookcell.book.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;

public class DefaultFavouriteService implements FavouriteService {
    private final FavouriteRepository favouriteRepository;

    public DefaultFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeFirst(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        Favourite favourite = new Favourite(customerId, book.getId());
        for (Favourite tempFavourite : getByCustomerId(customerId)) {
            if (tempFavourite.getBookId().equals(favourite.getBookId())) {
                System.out.println("THERE IS ALREADY " + formattedBookName + " IN FAVOURITES LIST");
                break;
            }
            favouriteRepository.add(new Favourite(customerId, book.getId()));
        }
    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeFirst(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        for (Favourite tempFavourite : getByCustomerId(customerId)) {
            if (tempFavourite.getBookId().equals(book.getId())) {
                favouriteRepository.remove(customerId, book.getId());
                break;
            }
            System.out.println("THERE IS NO " + formattedBookName + " IN FAVOURITES LIST!");
        }
    }

    @Override
    public List<Favourite> getByCustomerId(Integer customerId) {
        return favouriteRepository.getByCustomerId(customerId);
    }
}
