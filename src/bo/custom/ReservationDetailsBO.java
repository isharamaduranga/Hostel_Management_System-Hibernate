package bo.custom;

import bo.SuperBO;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import entity.CustomEntity;
import entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
     List getRoomIds() throws IOException;

     Room getRoomData(String id)throws Exception;

      List<CustomEntity> loadAllStudentDetails(String id) throws Exception;
}
