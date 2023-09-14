package tr.com.bookcell.user.customer;

import tr.com.bookcell.util.PatternMatcher;

import java.time.LocalDate;
import java.util.List;

import static tr.com.bookcell.util.InputFormatter.capitalizeFirst;
import static tr.com.bookcell.util.InputFormatter.capitalizeForMultipleStrings;
import static tr.com.bookcell.util.PatternMatcher.emailPattern;
import static tr.com.bookcell.util.PatternMatcher.passwordPattern;

public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(String email, String password, String name, String surname) {
        Customer customer = getByEmail(email);
        if(customer != null){
            System.out.println("You are already registered. Please enter your email and password.");
        }
        else {
            Integer integerCustomerType = emailPattern(email);
            CustomerType customerType = null;
            switch (integerCustomerType) {
                case (0) -> customerType = CustomerType.DEFAULT;
                case (1) -> customerType = CustomerType.STUDENT;
                case (2) -> customerType = CustomerType.VIP;
                default -> System.out.println("EMAIL ADDRESS IS NOT FOUND.");
            }
            if(customerType != null && passwordPattern(password)){
                String formattedName = capitalizeForMultipleStrings(name);
                String formattedSurname = capitalizeFirst(surname);
                Customer newCustomer = new Customer(password, formattedName, formattedSurname, LocalDate.now(), email);
                customerRepository.add(newCustomer);
                System.out.println("You have successfully registered!");
            }
        }
    }

    @Override
    public void remove(String email) {
        Customer customer = getByEmail(email);
        if(customer == null){
            System.out.println("THERE IS NO CUSTOMER WITH "+email+" IN CUSTOMERS LIST!");
        }
        else {
            for (Customer temp : getAll()) {
                if (temp.getEmail().equals(email)) {
                    customerRepository.remove(email);
                    return;
                }
            }
        }
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
        return null;
    }

    @Override
    public boolean isExist(String email, String password) {
        Customer customer = getByEmail(email);
        if(customer != null && customer.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }

}
