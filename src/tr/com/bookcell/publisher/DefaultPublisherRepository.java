package tr.com.bookcell.publisher;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultPublisherRepository implements PublisherRepository {
    private static final String INSERT_PUBLISHERS;

    static {
        INSERT_PUBLISHERS = "INSERT INTO public.\"PUBLISHER\"(\"NAME\") VALUES (?);";
    }

    private static final String SELECT_PUBLISHERS;

    static {
        SELECT_PUBLISHERS = "SELECT * FROM public.\"PUBLISHER\";";
    }

    private static final String DELETE_PUBLISHERS;

    static{
        DELETE_PUBLISHERS = "DELETE FROM public.\"PUBLISHER\" WHERE \"NAME\" = ?;";
    }
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
        try(Connection connection = connect()){
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_PUBLISHERS);
            while (resultSet.next()){
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt("ID"));
                publisher.setName(resultSet.getString("NAME"));
                publishers.add(publisher);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public void remove(Publisher publisher) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PUBLISHERS);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
