package tr.com.bookcell.user.customer;

import java.util.List;

public interface CustomerService {
    void add(String email, String password, String name, String surName);
    void remove(String email);
    List<Customer> getAll();
    Customer getByEmail(String email);
}
