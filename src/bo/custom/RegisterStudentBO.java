package bo.custom;

import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomReservationDTO;
import entity.Room;
import entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RegisterStudentBO {
     List getStudentIds() throws IOException;
     List getRoomIds() throws IOException;
     Student getStudent(String id) throws Exception;
     Room getRoom(String id) throws Exception ;
     String generateNewReservationID() throws SQLException, ClassNotFoundException, IOException;
     boolean addReservation(RoomReservationDTO reservationDTO) throws Exception ;
     String generateRoomAvailableStatus(String id)throws SQLException, ClassNotFoundException, IOException;
}
