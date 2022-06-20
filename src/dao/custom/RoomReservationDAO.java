package dao.custom;

import dao.CrudDAO;
import entity.RoomReservation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoomReservationDAO extends CrudDAO<RoomReservation,String> {
    String generateNewId() throws SQLException, ClassNotFoundException, IOException;
}
