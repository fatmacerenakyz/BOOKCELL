package tr.com.bookcell.user.customer;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultCustomerRepository implements CustomerRepository {
    private static final String INSERT_CUSTOMERS = "WITH rows AS (INSERT INTO public.\"USER\" (\"PASSWORD\") VALUES (?) RETURNING \"ID\") INSERT INTO public.\"CUSTOMER\" (\"ID\", \"NAME\", \"SURNAME\", \"REGISTRATION_DATE\", \"EMAIL\") SELECT \"ID\", ?, ?, ?, ? FROM rows;";
    private static final String DELETE_CUSTOMERS = "WITH rows AS (DELETE FROM public.\"CUSTOMER\" WHERE \"EMAIL\"=? RETURNING \"ID\") DELETE FROM public.\"USER\" WHERE \"ID\" IN (SELECT \"ID\" FROM rows);";
    private static final String SELECT_CUSTOMERS = "SELECT u.\"ID\", u.\"PASSWORD\", c.\"NAME\", c.\"SURNAME\", c.\"REGISTRATION_DATE\", c.\"EMAIL\" FROM public.\"USER\" u INNER JOIN public.\"CUSTOMER\" c ON u.\"ID\" = c.\"ID\";";
    private static final String SELECT_CUSTOMERS_WHERE_EMAIL = "SELECT u.\"ID\", u.\"PASSWORD\", c.\"NAME\", c.\"SURNAME\", c.\"REGISTRATION_DATE\", c.\"EMAIL\" FROM public.\"USER\" u INNER JOIN public.\"CUSTOMER\" c ON u.\"ID\" = c.\"ID\" WHERE \"EMAIL\"=?;";

    @Override
    public void add(Customer customer) {
        try (Connection connect = connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_CUSTOMERS);
            preparedStatement.setString(1, customer.getPassword());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void remove(String email) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMERS);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_CUSTOMERS);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("ID"));
                customer.setPassword(resultSet.getString("PASSWORD"));
                customer.setName(resultSet.getString("NAME"));
                customer.setSurname(resultSet.getString("SURNAME"));
                customer.setRegistrationDate(resultSet.getDate("REGISTRATION_DATE").toLocalDate());
                customer.setEmail(resultSet.getString("EMAIL"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getByEmail(String email) {
        Customer customer = new Customer();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer.setId(resultSet.getInt("ID"));
                customer.setPassword(resultSet.getString("PASSWORD"));
                customer.setName(resultSet.getString("NAME"));
                customer.setSurname(resultSet.getString("SURNAME"));
                customer.setRegistrationDate(resultSet.getDate("REGISTRATION_DATE").toLocalDate());
                customer.setEmail(resultSet.getString("EMAIL"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }


}
