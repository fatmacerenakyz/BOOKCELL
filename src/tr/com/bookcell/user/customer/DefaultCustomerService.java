package tr.com.bookcell.user.customer;

import java.time.LocalDate;
import java.util.List;

public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(String email, String password, String name, String surname) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setRegistrationDate(LocalDate.now());
        customerRepository.add(customer);
    }

    @Override
    public void remove(String email) {
        for (Customer temp : getAll()) {
            if (temp.getEmail().equals(email)) {
                customerRepository.remove(email);
                return;
            }
        }
        System.out.println(email + " WAS NOT FOUND.");
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getByEmail(String email) {
        for (Customer temp : getAll()) {
            if (temp.getEmail().equals(email)) {
                return customerRepository.getByEmail(email);
            }
        }
        System.out.println(email + " WAS NOT FOUND.");
        return null;
    }
}
