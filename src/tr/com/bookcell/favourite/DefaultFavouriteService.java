package tr.com.bookcell.favourite;

import tr.com.bookcell.book.*;

public class DefaultFavouriteService implements FavouriteService{
    private final FavouriteRepository favouriteRepository;
    BookRepository defaultBookRepository = new DefaultBookRepository();
    BookService defaultBookService = new DefaultBookService(defaultBookRepository);
    Book book = new Book();


    public DefaultFavouriteService(FavouriteRepository favouriteRepository) {
        this.favouriteRepository = favouriteRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname) {
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Favourite favourite = new Favourite(customerId, book.getId());
        favouriteRepository.add(favourite);
    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        favouriteRepository.remove(customerId, book.getId());
    }
}
