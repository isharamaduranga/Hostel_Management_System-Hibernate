/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 12:06 AM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.ReservationDetailsBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.RoomDAO;
import entity.CustomEntity;
import entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsBOImpl implements ReservationDetailsBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

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
