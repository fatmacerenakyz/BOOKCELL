package tr.com.bookcell.user.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DefaultAdminRepository implements AdminRepository {
    private static final String INSERT_ADMIN = "WITH rows AS (INSERT INTO public.\"USER\" (\"PASSWORD\") VALUES (?) RETURNING \"ID\") INSERT INTO public.\"ADMIN\" (\"ID\", \"USERNAME\") SELECT \"ID\", ? FROM rows;";
    private static final String SELECT_ADMIN = "SELECT u.\"ID\", u.\"PASSWORD\", a.\"USERNAME\" FROM public.\"USER\" u INNER JOIN public.\"ADMIN\" a ON u.\"ID\" = a.\"ID\";";
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

    @Override
    public Admin get() {
        Admin admin = new Admin();
        try(Connection connection = connect()){
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ADMIN);
            while(resultSet.next()){
                admin.setId(resultSet.getInt("ID"));
                admin.setPassword(resultSet.getString("PASSWORD"));
                admin.setUserName(resultSet.getString("USERNAME"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return admin;
    }


}
