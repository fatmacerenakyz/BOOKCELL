package tr.com.bookcell.favourite;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DefaultFavouriteRepository implements FavouriteRepository {
    private static final String INSERT_FAVOURITES = "INSERT INTO public.\"FAVOURITE\"(\"CUSTOMER_ID\", \"BOOK_ID\") VALUES (?, ?);";
    private static final String DELETE_FAVOURITES = "DELETE FROM public.\"FAVOURITE\" WHERE \"CUSTOMER_ID\" = ? AND \"BOOK_ID\" = ?;";

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
}
