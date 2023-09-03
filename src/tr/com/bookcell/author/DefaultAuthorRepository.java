package tr.com.bookcell.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorRepository implements AuthorRepository {
    private static final String SELECT_AUTHORS = "SELECT * FROM public.\"AUTHOR\";";
    private static final String INSERT_AUTHORS = "INSERT INTO public.\"AUTHOR\"(\"NAME\", \"SURNAME\") VALUES (?, ?);";
    private static final String SELECT_AUTHORS_WHERE_NAME = "SELECT * FROM public.\"AUTHOR\" WHERE \"NAME\" = ?;";
    private static final String DELETE_AUTHORS = "DELETE FROM public.\"AUTHOR\" WHERE \"NAME\" = ? AND \"SURNAME\" = ?;";

    @Override
    public void add(Author author) {

        try (Connection connect = connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_AUTHORS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public List<Author> getAll() {
        ArrayList<Author> authors = new ArrayList<>();
        try (Connection connection = connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_AUTHORS);
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("ID"));
                author.setName(resultSet.getString("NAME"));
                author.setSurname(resultSet.getString("SURNAME"));
                authors.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> getByName(String name) {
        ArrayList<Author> authors = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHORS_WHERE_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("ID"));
                author.setName(resultSet.getString("NAME"));
                author.setSurname(resultSet.getString("SURNAME"));
                authors.add(author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public void remove(Author author) {
        try(Connection connection =  connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHORS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
