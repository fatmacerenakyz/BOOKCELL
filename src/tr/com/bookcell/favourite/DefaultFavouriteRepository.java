package tr.com.bookcell.favourite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultFavouriteRepository implements FavouriteRepository {
    private static final String INSERT_FAVOURITES = "INSERT INTO public.\"FAVOURITE\"(\"CUSTOMER_ID\", \"BOOK_ID\") VALUES (?, ?);";
    private static final String DELETE_FAVOURITES = "DELETE FROM public.\"FAVOURITE\" WHERE \"CUSTOMER_ID\" = ? AND \"BOOK_ID\" = ?;";

    private static final String SELECT_FAVOURITES_WITH_CUSTOMER_ID = "SELECT * FROM public.\"LANDING\" WHERE \"CUSTOMER_ID\"=?;";

    @Override
    public void add(Favourite favourite) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAVOURITES);
            preparedStatement.setInt(1, favourite.getCustomerId());
            preparedStatement.setInt(2, favourite.getBookId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Integer customerId, Integer bookId) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVOURITES);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Favourite> getByCustomer(Integer customerId) {
        ArrayList<Favourite> favourites = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAVOURITES_WITH_CUSTOMER_ID);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Favourite favourite = new Favourite();
                favourite.setId(resultSet.getInt("ID"));
                favourite.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                favourite.setBookId(resultSet.getInt("BOOK_ID"));
                favourites.add(favourite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favourites;
    }
}
