package tr.com.bookcell.basket;

import tr.com.bookcell.book.*;
import tr.com.bookcell.user.customer.*;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;

public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;

    public DefaultBasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
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
        Book book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);

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
        BookRepository defaultBookRepository = new DefaultBookRepository();
        BookService defaultBookService = new DefaultBookService(defaultBookRepository);
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Book book = defaultBookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
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
        CustomerRepository defaultCustomerRepository = new DefaultCustomerRepository();
        CustomerService defaultCustomerService = new DefaultCustomerService(defaultCustomerRepository);
        Customer customer = defaultCustomerService.getByEmail(customerEmail);
        return basketRepository.getByCustomerId(customer.getId());
    }


}
