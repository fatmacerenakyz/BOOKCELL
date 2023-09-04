package tr.com.bookcell.publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultPublisherRepository implements PublisherRepository {
    private static final String INSERT_PUBLISHERS = "INSERT INTO public.\"PUBLISHER\"(\"NAME\") VALUES (?);";
    private static final String SELECT_PUBLISHERS = "SELECT * FROM public.\"PUBLISHER\";";
    private static final String DELETE_PUBLISHERS = "DELETE FROM public.\"PUBLISHER\" WHERE \"NAME\" = ?;";
    private static final String SELECT_PUBLISHERS_WHERE_NAME = "SELECT * FROM public.\"PUBLISHER\" WHERE \"NAME\" = ?;";

    @Override
    public void add(Publisher publisher) {
        try (Connection connect = connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_PUBLISHERS);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Publisher> getAll() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        try (Connection connection = connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_PUBLISHERS);
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("ID"));
                publisher.setName(resultSet.getString("NAME"));
                publishers.add(publisher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public Publisher getByName(String name) {
        Publisher publisher = new Publisher();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHERS_WHERE_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                publisher.setId(resultSet.getInt("ID"));
                publisher.setName(resultSet.getString("NAME"));
            } else {
                System.out.println("THERE IS NO DATA IN THIS TABLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publisher;
    }

    @Override
    public void remove(String name) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PUBLISHERS);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
