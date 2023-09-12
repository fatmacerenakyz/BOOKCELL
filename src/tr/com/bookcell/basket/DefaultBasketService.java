package tr.com.bookcell.basket;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;
    private final BookService bookService;
    private final CustomerService customerService;
    public DefaultBasketService(BasketRepository basketRepository, BookService bookService, CustomerService customexrService) {
        this.basketRepository = basketRepository;
        this.bookService = bookService;
        this.customerService = customexrService;
    }

    @Override
    public void add(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);
        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);

        if(book!=null){
            boolean bool = false;
            for(Basket tempBasket : getByCustomerEmail(customerEmail)){
                if(tempBasket.getBookId().equals(book.getId())){
                    System.out.println("THERE IS ALREADY "+formattedBookName+" IN YOUR BASKET");
                    bool = true;
                    break;
                }
            }
            if(!bool){
                Basket basket = new Basket(customer.getId(), book.getId());
                basketRepository.add(basket);

            }
        }
    }

    @Override
    public void remove(String customerEmail, String bookName, String authorName, String authorSurname) {
        String formattedBookName = capitalizeForBookName(bookName);
        String formattedAuthorName = capitalizeForMultipleStrings(authorName);
        String formattedAuthorSurname = capitalizeFirst(authorSurname);

        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if(book != null){
            for(Basket tempBasket : getByCustomerEmail(customerEmail)){
                if(tempBasket.getBookId().equals(book.getId())){
                    basketRepository.remove(customer.getId(),book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO " + formattedBookName + " IN YOUR BASKETS!");
        }
    }

    @Override
    public List<Basket> getByCustomerEmail(String customerEmail) {

        Customer customer = customerService.getByEmail(customerEmail);
        return basketRepository.getByCustomerId(customer.getId());
    }


}
