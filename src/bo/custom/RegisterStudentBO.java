package bo.custom;

import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import entity.Room;
import entity.Student;

import java.io.IOException;
import java.util.List;

public interface RegisterStudentBO {
     List getStudentIds() throws IOException;
     List getRoomIds() throws IOException;
     Student getStudent(String id) throws Exception;
     Room getRoom(String id) throws Exception ;
}
