package tr.com.bookcell.reservation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultReservationRepository implements ReservationRepository {
    private static final String INSERT_RESERVATIONS = "INSERT INTO public.\"RESERVATION\"(\"CUSTOMER_ID\", \"BOOK_ID\", \"START_DATE\", \"DELIVERY_DATE\", \"IS_CANCELED\", \"IS_PICKED_UP\") VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_RESERVATIONS_IS_CANCELED_WHERE_CUSTOMER_ID_BOOK_ID_START_DATE = "UPDATE public.\"RESERVATION\" SET \"IS_CANCELED\"=? WHERE \"CUSTOMER_ID\" = ? AND \"BOOK_ID\" = ? AND \"START_DATE\"=?;";
    private static final String UPDATE_RESERVATIONS_START_DATE = "UPDATE public.\"RESERVATION\" SET \"START_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"DELIVERY_DATE\"=?;";
    private static final String UPDATE_RESERVATIONS_DELIVERY_DATE = "UPDATE public.\"RESERVATION\" SET \"DELIVERY_DATE\"=? WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"START_DATE\"=?;";
    private static final String SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID_START_DATE = "SELECT * FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"START_DATE\"=?;";
    private static final String SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID_DELIVERY_DATE = "SELECT * FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=? AND \"DELIVERY_DATE\"=?;";
    private static final String SELECT_RESERVATIONS_WHERE_CUSTOMER_ID = "SELECT * FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\"=?;";
    private static final String SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID = "SELECT * FROM public.\"RESERVATION\" WHERE \"CUSTOMER_ID\"=? AND \"BOOK_ID\"=?;";

    @Override
    public void add(Reservation reservation) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATIONS);
            preparedStatement.setInt(1, reservation.getCustomerId());
            preparedStatement.setInt(2, reservation.getBookId());
            preparedStatement.setDate(3, Date.valueOf(reservation.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getDeliveryDate()));
            preparedStatement.setBoolean(5, reservation.isCanceled());
            preparedStatement.setBoolean(6, reservation.isPickedUp());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCanceled(Integer customerId, Integer bookId, LocalDate startDate) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATIONS_IS_CANCELED_WHERE_CUSTOMER_ID_BOOK_ID_START_DATE);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setDate(4, Date.valueOf(startDate));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStartDate(Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATIONS_START_DATE);
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setDate(4, Date.valueOf(deliveryDate));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDeliveryDate(Integer customerId, Integer bookId, LocalDate startDate, LocalDate deliveryDate) {
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATIONS_DELIVERY_DATE);
            preparedStatement.setDate(1, Date.valueOf(deliveryDate));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, bookId);
            preparedStatement.setDate(4, Date.valueOf(startDate));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation getByStartDate(Integer customerId, Integer bookId, LocalDate startDate) {
        Reservation reservation = new Reservation();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID_START_DATE);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setDate(3, Date.valueOf(startDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation.setId(resultSet.getInt("ID"));
                reservation.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                reservation.setBookId(resultSet.getInt("BOOK_ID"));
                reservation.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                reservation.setDeliveryDate(resultSet.getDate("DELIVERY_DATE").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }
    @Override
    public Reservation getByDeliveryDate(Integer customerId, Integer bookId, LocalDate deliveryDate) {
        Reservation reservation = new Reservation();
        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID_DELIVERY_DATE);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.setDate(3, Date.valueOf(deliveryDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservation.setId(resultSet.getInt("ID"));
                reservation.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                reservation.setBookId(resultSet.getInt("BOOK_ID"));
                reservation.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                reservation.setDeliveryDate(resultSet.getDate("DELIVERY_DATE").toLocalDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }
    @Override
    public List<Reservation> getByCustomer(Integer customerId) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATIONS_WHERE_CUSTOMER_ID);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("ID"));
                reservation.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                reservation.setBookId(resultSet.getInt("BOOK_ID"));
                reservation.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                reservation.setDeliveryDate(resultSet.getDate("DELIVERY_DATE").toLocalDate());
                reservations.add(reservation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public List<Reservation> getByCustomerAndBook(Integer customerId, Integer bookId) {
        List<Reservation> reservations = new ArrayList<>();
        try(Connection connection = connect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATIONS_WHERE_CUSTOMER_ID_BOOK_ID);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("ID"));
                reservation.setCustomerId(resultSet.getInt("CUSTOMER_ID"));
                reservation.setBookId(resultSet.getInt("BOOK_ID"));
                reservation.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                reservation.setDeliveryDate(resultSet.getDate("DELIVERY_DATE").toLocalDate());
                reservations.add(reservation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reservations;
    }
}
