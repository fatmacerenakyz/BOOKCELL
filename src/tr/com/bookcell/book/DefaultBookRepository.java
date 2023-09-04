package tr.com.bookcell.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookRepository implements BookRepository {
    private static final String INSERT_BOOKS = "INSERT INTO public.\"BOOK\"(\"NAME\", \"AUTHOR_ID\", \"PUBLISHER_ID\", \"GENRE\", \"PUBLICATION_YEAR\", \"PAGE_NUMBER\", \"IS_AVAILABLE\") VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_BOOKS = "SELECT * FROM public.\"BOOK\";";
    private static final String DELETE_BOOKS = "DELETE FROM public.\"BOOK\" WHERE \"NAME\" = ? AND \"AUTHOR_ID\" = ?;";
    private static final String UPDATE_BOOKS_IS_AVAILABLE = "UPDATE public.\"BOOK\" SET \"IS_AVAILABLE\"=? WHERE \"NAME\"=? AND \"AUTHOR_ID\"=?;";
    private static final String SELECT_BOOKS_WHERE_NAME = "SELECT * FROM public.\"BOOK\" WHERE \"NAME\" = ?;";
    private static final String SELECT_BOOKS_WHERE_NAME_AND_AUTHOR_ID = "SELECT * FROM public.\"BOOK\" WHERE \"NAME\" = ? AND \"AUTHOR_ID\" = ?;";

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
    public List<Book> getByName(String name) {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_WHERE_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    public Book getByNameAndAuthor(String name, Integer authorId) {
        Book book = new Book();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS_WHERE_NAME_AND_AUTHOR_ID);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                book.setId(resultSet.getInt("ID"));
                book.setName(resultSet.getString("NAME"));
                book.setAuthorId(resultSet.getInt("AUTHOR_ID"));
                book.setPublisherId(resultSet.getInt("PUBLISHER_ID"));
                book.setGenre(resultSet.getString("GENRE"));
                book.setPublicationYear(resultSet.getInt("PUBLICATION_YEAR"));
                book.setPageNumber(resultSet.getInt("PAGE_NUMBER"));
                book.setAvailable(resultSet.getBoolean("IS_AVAILABLE"));
            } else {
                System.out.println("THERE IS NO DATA IN THIS TABLE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void remove(String name, Integer authorId) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, authorId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAvailable(String name, Integer authorId, boolean isAvailable) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_IS_AVAILABLE);
            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, authorId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
