package tr.com.bookcell.basket;

import tr.com.bookcell.book.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;

    public DefaultBasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void add(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        if(book!=null){
            boolean bool = false;
            for(Basket tempBasket : getByCustomerId(customerId)){
                if(tempBasket.getBookId().equals(book.getId())){
                    System.out.println("THERE IS ALREADY "+formattedBookName+" IN YOUR BASKET");
                    bool = true;
                    break;
                }
            }
            if(!bool){
                Basket basket = new Basket(customerId, book.getId());
                basketRepository.add(basket);

            }
        }
    }

    @Override
    public void remove(Integer customerId, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeFirst(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        Book book = new Book();
        book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        if(book != null){
            for(Basket tempBasket : getByCustomerId(customerId)){
                if(tempBasket.getBookId().equals(book.getId())){
                    basketRepository.remove(customerId,book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO " + formattedBookName + " IN YOUR BASKETS!");
        }
    }

    @Override
    public List<Basket> getByCustomerId(Integer customerId) {
        return basketRepository.getByCustomerId(customerId);
    }


}
