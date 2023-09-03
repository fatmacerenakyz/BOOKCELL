package tr.com.bookcell.book;

import tr.com.bookcell.author.Author;

import javax.xml.transform.Result;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static tr.com.bookcell.util.DatabaseConnector.connect;

public class DefaultBookRepository implements BookRepository {
    private static final String INSERT_BOOKS;
    private static final String SELECT_BOOKS;
    private static final String DELETE_BOOKS;
    private static final String UPDATE_BOOKS_IS_AVAILABLE;

    static {
        INSERT_BOOKS = "INSERT INTO public.\"BOOK\"(\"NAME\", \"AUTHOR_ID\", \"PUBLISHER_ID\", \"GENRE\", \"PUBLICATION_YEAR\", \"PAGE_NUMBER\", \"IS_AVAILABLE\") VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    static {
        SELECT_BOOKS = "SELECT * FROM public.\"BOOK\";";
    }

    static {
        DELETE_BOOKS = "DELETE FROM public.\"BOOK\" WHERE \"NAME\" = ? AND \"AUTHOR_ID\" = ?;";
    }

    static {
        UPDATE_BOOKS_IS_AVAILABLE = "UPDATE public.\"BOOK\" SET \"IS_AVAILABLE\"=? WHERE \"NAME\"=? AND \"AUTHOR_ID\"=?;";
    }

    @Override
    public void add(Book book) {
        try (Connection connect = connect()) {
            PreparedStatement preparedStatement = connect.prepareStatement(INSERT_BOOKS);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getPublisherId());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setInt(5, book.getPublicationYear());
            preparedStatement.setInt(6, book.getPageNumber());
            preparedStatement.setBoolean(7, book.isAvailable());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public List<Book> getAll() {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_BOOKS);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                book.setPublisherId(resultSet.getInt("PUBLISHER_ID"));
                book.setGenre(resultSet.getString("GENRE"));
                book.setPublicationYear(resultSet.getInt("PUBLICATION_YEAR"));
                book.setPageNumber(resultSet.getInt("PAGE_NUMBER"));
                book.setAvailable(resultSet.getBoolean("IS_AVAILABLE"));
                books.add(book);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void remove(Book book) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKS);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAvailable(Book book) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_IS_AVAILABLE);
            preparedStatement.setBoolean(1, book.isAvailable());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setInt(3, book.getAuthorId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
