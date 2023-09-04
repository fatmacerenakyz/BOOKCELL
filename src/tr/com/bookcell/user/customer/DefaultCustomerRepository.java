package tr.com.bookcell.user.customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import static tr.com.bookcell.util.DatabaseConnector.connect;

public class DefaultCustomerRepository implements CustomerRepository {
    private static final String INSERT_CUSTOMERS;
    static {
        INSERT_CUSTOMERS = "WITH rows AS (INSERT INTO \"USER\" (\"EMAIL\", \"PASSWORD\") VALUES (?, ?) RETURNING \"ID\") INSERT INTO \"CUSTOMER\" (\"ID\", \"NAME\", \"SURNAME\", \"REGISTIRATION_DATE\") SELECT \"ID\", ?, ?, ? FROM rows;";
    }




    @Override
    public void add(Customer customer) {
        try (Connection connect = connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_CUSTOMERS);
            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getSurname());
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
