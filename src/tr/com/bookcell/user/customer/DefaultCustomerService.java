package tr.com.bookcell.user.customer;

import java.time.LocalDate;
import java.util.List;

import static tr.com.bookcell.util.InputFormatter.*;
import static tr.com.bookcell.util.PatternMatcher.emailPattern;
import static tr.com.bookcell.util.PatternMatcher.passwordPattern;
import static tr.com.bookcell.util.TestClassMethods.*;

public class DefaultCustomerService implements CustomerService {
    private final CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean add(String email, String password, String name, String surname) {
        String formattedEmail = lowerCaseForEmail(email);
        Customer customer = getByEmail(formattedEmail);
        if (customer != null) {
            return false;
        } else {
            Integer integerCustomerType = emailPattern(formattedEmail);
            CustomerType customerType = null;
            switch (integerCustomerType) {
                case (0) -> customerType = CustomerType.DEFAULT;
                case (1) -> customerType = CustomerType.STUDENT;
                case (2) -> customerType = CustomerType.VIP;
                default -> {
                    System.out.println(ansiColorRed() + "EMAIL ADDRESS IS NOT PROPER." + ansiColorReset());
                    return false;
                }
            }
            if (passwordPattern(password)) {
                String formattedName = capitalizeForMultipleStrings(name);
                String formattedSurname = capitalizeFirst(surname);
                Customer newCustomer = new Customer(password, formattedName, formattedSurname, LocalDate.now(), formattedEmail);
                customerRepository.add(newCustomer);
                System.out.println(ansiColorGreen() + "YOU HAVE SUCCESSFULLY REGISTERED!" + ansiColorReset());
                return true;
            } else {
                System.out.println(ansiColorRed() + "THIS PASSWORD IS NOT VALID." + ansiColorReset());
            }
        }
        return false;
    }

    @Override
    public void remove(String email) {
        String formattedEmail = lowerCaseForEmail(email);
        Customer customer = getByEmail(formattedEmail);
        if (customer == null) {
            System.out.println(ansiColorRed() + "THERE IS NO CUSTOMER WITH " + formattedEmail + " IN CUSTOMERS LIST!" + ansiColorReset());
        } else {

            customerRepository.remove(formattedEmail);

        }

    }


    @Override
    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    @Override
    public Customer getByEmail(String email) {
        for (Customer temp : getAll()) {
            if (temp.getEmail().equalsIgnoreCase(email)) {
                return customerRepository.getByEmail(email.toLowerCase());
            }
        }
        return null;
    }

    @Override
    public boolean isPasswordCorrect(String email, String password) {
        String formattedEmail = lowerCaseForEmail(email);
        Customer customer = getByEmail(formattedEmail);
        return customer != null && customer.getPassword().equals(password);
    }

}
