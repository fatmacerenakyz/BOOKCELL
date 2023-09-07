package tr.com.bookcell.basket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DefaultBasketRepository implements BasketRepository {
    private static final String INSERT_BASKETS = "INSERT INTO public.\"BASKET\"(\"CUSTOMER_ID\", \"BOOK_ID\") VALUES (?, ?);";
    private static final String DELETE_BASKETS_WITH_CUSTOMER_ID_BOOK_ID = "DELETE FROM public.\"BASKET\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";
    private static final String SELECT_BASKETS_WITH_CUSTOMER_ID = "SELECT * FROM public.\"BASKET\" WHERE \"CUSTOMER_ID\"=?;";

    @Override
    public void add(Basket basket) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BASKETS);
            preparedStatement.setInt(1, basket.getCustomerId());
            preparedStatement.setInt(2, basket.getBookId());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Integer customerId, Integer bookId) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKETS_WITH_CUSTOMER_ID_BOOK_ID);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Basket> getByCustomerId(Integer customerId) {
        ArrayList<Basket> baskets = new ArrayList<>();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BASKETS_WITH_CUSTOMER_ID);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Basket basket = new Basket();
                basket.setId(resultSet.getInt("ID"));
                basket.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                basket.setBookId(resultSet.getInt("BOOK_ID"));
                baskets.add(basket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baskets;
    }


}
