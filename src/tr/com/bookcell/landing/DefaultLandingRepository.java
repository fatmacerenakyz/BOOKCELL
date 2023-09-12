package tr.com.bookcell.landing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultLandingRepository implements LandingRepository {
    private static final String INSERT_LANDINGS = "INSERT INTO public.\"LANDING\"(\"CUSTOMER_ID\", \"BOOK_ID\", \"PICK_UP_DATE\", \"DROP_OFF_DATE\") VALUES (?, ?, ?, ?);";
    private static final String UPDATE_LANDINGS_DROP_OFF_DATE = "UPDATE public.\"LANDING\" SET \"DROP_OFF_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"PICK_UP_DATE\"=?;";
    private static final String SELECT_LANDINGS = "SELECT * FROM public.\"LANDING\";";
    private static final String SELECT_LANDINGS_WITH_CUSTOMER_AND_BOOK = "SELECT* FROM public.\"LANDING\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";

    @Override
    public void setPickUp(Landing landing) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANDINGS);
            preparedStatement.setInt(1, landing.getCustomerId());
            preparedStatement.setInt(2, landing.getBookId());
            preparedStatement.setDate(3, Date.valueOf(landing.getPickUpDate()));
            preparedStatement.setDate(4, null);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDropOff(Landing landing, LocalDate pickUpDate) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LANDINGS_DROP_OFF_DATE);
            preparedStatement.setDate(1, Date.valueOf(landing.getDropOffDate()));
            preparedStatement.setInt(2, landing.getCustomerId());
            preparedStatement.setInt(3, landing.getBookId());
            preparedStatement.setDate(4, Date.valueOf(pickUpDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Landing> getByCustomerAndBook(Integer customerId, Integer bookId) {
        List<Landing> landings = new ArrayList<>();
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LANDINGS_WITH_CUSTOMER_AND_BOOK);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Landing landing = new Landing();
                landing.setId(resultSet.getInt("ID"));
                landing.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                landing.setBookId(resultSet.getInt("BOOK_ID"));
                landing.setPickUpDate(resultSet.getDate("PICK_UP_DATE").toLocalDate());
                landing.setDropOffDate(resultSet.getDate("DROP_OFF_DATE").toLocalDate());
                landings.add(landing);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return landings;
    }

    @Override
    public List<Landing> getAll() {
        List<Landing> landings = new ArrayList<>();
        try (Connection connection = connect()) {
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_LANDINGS);
            while (resultSet.next()) {
                Landing landing = new Landing();
                landing.setId(resultSet.getInt("ID"));
                landing.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                landing.setBookId(resultSet.getInt("BOOK_ID"));
                landing.setPickUpDate(resultSet.getDate("PICK_UP_DATE").toLocalDate());
                landing.setDropOffDate(resultSet.getDate("DROP_OFF_DATE").toLocalDate());
                landings.add(landing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return landings;
    }
}
