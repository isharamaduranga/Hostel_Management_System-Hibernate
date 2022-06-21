/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 12:06 AM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.ReservationDetailsBO;
import dao.custom.RoomDAO;
import dao.custom.impl.RoomDAOImpl;
import entity.Room;

import java.io.IOException;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {
    @Override
    public List getRoomIds() throws IOException {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.getRoomIds();
    }
    @Override
    public Room getRoomData(String id)throws Exception {
        RoomDAO roomDAO = new RoomDAOImpl();
      return   roomDAO.find(id);
    }
}
