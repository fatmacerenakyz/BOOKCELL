package tr.com.bookcell.user.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DefaultAdminRepository implements AdminRepository {
    private static final String INSERT_ADMIN = "WITH rows AS (INSERT INTO \"USER\" (\"PASSWORD\") VALUES (?) RETURNING \"ID\") INSERT INTO \"ADMIN\" (\"ID\", \"USERNAME\") SELECT \"ID\", ? FROM rows;";
    private static final String SELECT_ADMIN = "SELECT * FROM public.\"ADMIN\" WHERE \"USERNAME\" = ?;";
    @Override
    public void add(Admin admin) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN);
            preparedStatement.setString(1, admin.getPassword());
            preparedStatement.setString(2, admin.getUserName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
