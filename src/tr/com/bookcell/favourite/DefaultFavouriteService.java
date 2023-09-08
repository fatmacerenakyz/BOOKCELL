package tr.com.bookcell.favourite;

import tr.com.bookcell.book.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultFavouriteService implements FavouriteService {
    private final FavouriteRepository favouriteRepository;

    public DefaultFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            boolean bool = false;
            for (Favourite tempFavourite : getByCustomerId(customerId)) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    System.out.println("THERE IS ALREADY " + formattedBookName + " IN FAVOURITES LIST");
                    bool = true;
                    break;
                }
            }
            if(!bool){
                Favourite favourite = new Favourite(customerId, book.getId());
                favouriteRepository.add(favourite);
            }
        }
    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(formattedBookName, formattedAuthorName, formattedAuthorSurname);
        if (book != null) {
            for (Favourite tempFavourite : getByCustomerId(customerId)) {
                if (tempFavourite.getBookId().equals(book.getId())) {
                    favouriteRepository.remove(customerId, book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO " + formattedBookName + " IN FAVOURITES LIST!");
        }

    }

    @Override
    public List<Favourite> getByCustomerId(Integer customerId) {
        return favouriteRepository.getByCustomerId(customerId);
    }
}
