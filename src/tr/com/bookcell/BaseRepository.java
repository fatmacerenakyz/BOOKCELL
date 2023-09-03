package tr.com.bookcell;

import tr.com.bookcell.util.DatabaseConnector;

import java.sql.Connection;

public interface BaseRepository {
    default Connection connect() {
        return DatabaseConnector.connect();
    }
}
