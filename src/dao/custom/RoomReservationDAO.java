package dao.custom;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoomReservationDAO {
    String generateNewId() throws SQLException, ClassNotFoundException, IOException;
}
