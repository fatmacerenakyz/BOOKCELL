package tr.com.bookcell.util;

import tr.com.bookcell.config.DatabaseCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection connect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DatabaseCredentials.URL, DatabaseCredentials.USERNAME, DatabaseCredentials.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
