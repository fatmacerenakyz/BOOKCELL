package tr.com.bookcell.landing;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class DefaultLandingRepository implements LandingRepository {
    private static final String INSERT_LANDINGS = "INSERT INTO public.\"LANDING\"(\"CUSTOMER_ID\", \"BOOK_ID\", \"PICK_UP_DATE\", \"DROP_OFF_DATE\") VALUES (?, ?, ?, ?);";
    private static final String UPDATE_LANDINGS_DROP_OFF_DATE = "UPDATE public.\"LANDING\" SET \"DROP_OFF_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"PICK_UP_DATE\"=?;";
    @Override
    public void setPickUp(Landing landing) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANDINGS);
            preparedStatement.setInt(1, landing.getCustomerId());
            preparedStatement.setInt(2, landing.getBookId());
            preparedStatement.setDate(3, Date.valueOf(landing.getPickUpDate()));
            preparedStatement.setDate(4, null);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setDropOff(Integer customerId, Integer bookId, LocalDate dropOffDate, Date pickUpDate) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LANDINGS_DROP_OFF_DATE);
            preparedStatement.setDate(1, Date.valueOf(dropOffDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setDate(4, pickUpDate);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
