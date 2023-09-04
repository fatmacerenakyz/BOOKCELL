package tr.com.bookcell.user.customer;

import java.time.LocalDate;

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
}
