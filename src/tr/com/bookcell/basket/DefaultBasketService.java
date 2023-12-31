package tr.com.bookcell.basket;

import tr.com.bookcell.book.Book;
import tr.com.bookcell.book.BookService;
import tr.com.bookcell.user.customer.Customer;
import tr.com.bookcell.user.customer.CustomerService;

import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.TestClassMethods.ansiColorRed;
import static tr.com.bookcell.util.TestClassMethods.ansiColorReset;

public class DefaultBasketService implements BasketService {
    private final BasketRepository basketRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    public DefaultBasketService(BasketRepository basketRepository, BookService bookService, CustomerService customerService) {
        this.basketRepository = basketRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Override
    public boolean add(String customerEmail, String bookName, String authorName, String authorSurname) {
        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);

        if (book != null) {
            for (Basket tempBasket : getByCustomer(customerEmail)) {
                if (tempBasket.getBookId().equals(book.getId())) {
                    System.out.println("THERE IS ALREADY " + bookName + " IN YOUR BASKET");
                    return false;
                }
            }

            Basket basket = new Basket(customer.getId(), book.getId());
            basketRepository.add(basket);
            return true;
        }
        System.out.println(ansiColorRed()+"THERE IS NO "+bookName+" IN BOOKS LIST!"+ansiColorReset());
        return false;
    }

    @Override
    public void remove(String customerEmail, String bookName, String authorName, String authorSurname) {
        Book book = bookService.getByNameAndAuthor(bookName, authorName, authorSurname);
        Customer customer = customerService.getByEmail(customerEmail);
        if (book != null && customer != null) {
            for (Basket tempBasket : getByCustomer(customerEmail)) {
                if (tempBasket.getBookId().equals(book.getId())) {
                    basketRepository.remove(customer.getId(), book.getId());
                    return;
                }
            }
            System.out.println("THERE IS NO " + bookName + " IN YOUR BASKETS!");
        }
    }

    @Override
    public List<Basket> getByCustomer(String customerEmail) {
        Customer customer = customerService.getByEmail(customerEmail);
        if (customer != null) {
            return basketRepository.getByCustomer(customer.getId());
        } else {
            return null;
        }
    }

    @Override
    public List<Basket> getAll() {
        return basketRepository.getAll();
    }


}
