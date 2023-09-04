package tr.com.bookcell.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DefaultReservationRepository implements ReservationRepository {
    private static final String INSERT_RESERVATIONS;
    private static final String DELETE_RESERVATIONS;
    private static final String UPDATE_RESERVATIONS_START_DATE;
    private static final String UPDATE_RESERVATIONS_DELIVERY_DATE;
    private static final String SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID;

    static {
        INSERT_RESERVATIONS = "INSERT INTO public.\"RESERVATION\"(\"CUSTOMER_ID\", \"BOOK_ID\", \"START_DATE\", \"EXPIRY_DATE\") VALUES (?, ?, ?, ?);";
    }

    static {
        DELETE_RESERVATIONS = "DELETE FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\" = ? AND \"BOOK_ID\" = ?;";
    }

    static {
        UPDATE_RESERVATIONS_START_DATE = "UPDATE public.\"RESERVATION\" SET \"START_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";
    }

    static {
        UPDATE_RESERVATIONS_DELIVERY_DATE = "UPDATE public.\"RESERVATION\" SET \"DELIVERY_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";
    }
    static{
        SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID = "SELECT * FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";
    }

    @Override
    public void add(Reservation reservation) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATIONS);
            preparedStatement.setInt(1, reservation.getCustomerId());
            preparedStatement.setInt(2, reservation.getBookId());
            preparedStatement.setDate(3, Date.valueOf(reservation.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getDeliveryDate()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Integer customerId, Integer bookId) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATIONS);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStartDate(Integer customerId, Integer bookId, LocalDate startDate) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATIONS_START_DATE);
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setDeliveryDate(Integer customerId, Integer bookId, LocalDate expiryDate) {
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATIONS_DELIVERY_DATE);
            preparedStatement.setDate(1, Date.valueOf(expiryDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getByCustomerAndBook(Integer customerId, Integer bookId) {
        Reservation reservation = new Reservation();
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                reservation.setId(resultSet.getInt("ID"));
                reservation.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                reservation.setBookId(resultSet.getInt("BOOK_ID"));
                reservation.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                reservation.setDeliveryDate(resultSet.getDate("DELIVERY_DATE").toLocalDate());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reservation;
    }
}
