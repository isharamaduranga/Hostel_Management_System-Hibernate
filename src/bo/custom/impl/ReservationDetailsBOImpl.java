/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 12:06 AM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.ReservationDetailsBO;
import dao.custom.QueryDAO;
import dao.custom.RoomDAO;
import dao.custom.impl.QueryDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import entity.CustomEntity;
import entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {

    RoomDAO roomDAO = new RoomDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public List getRoomIds() throws IOException {
        return roomDAO.getRoomIds();
    }

    @Override
    public Room getRoomData(String id) throws Exception {

        return roomDAO.find(id);
    }

    @Override
    public List<CustomEntity> loadAllStudentDetails(String id) throws Exception {

        List<CustomEntity> studentDetails = queryDAO.getStudentDetails(id);

        ArrayList<CustomEntity> entityArrayList = new ArrayList<>();

        for (CustomEntity studentDetail : studentDetails) {
            entityArrayList.add(studentDetail);
        }
        return entityArrayList;
    }
}
