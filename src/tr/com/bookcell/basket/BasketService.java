package tr.com.bookcell.basket;

import java.util.List;

public interface BasketService {
    boolean add(String customerEmail, String bookName, String authorName, String authorSurname);
    void remove(String customerEmail, String bookName, String authorName, String authorSurname);
    List<Basket> getByCustomer(String customerEmail);

    List<Basket> getAll();


}
