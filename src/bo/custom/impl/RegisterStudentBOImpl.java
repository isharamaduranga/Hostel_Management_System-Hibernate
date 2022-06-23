/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/18/2022
 * Time        : 1:13 PM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.RegisterStudentBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.RoomReservationDAO;
import dao.custom.StudentDAO;
import dto.RoomReservationDTO;
import entity.Room;
import entity.RoomReservation;
import entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RegisterStudentBOImpl implements RegisterStudentBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final RoomReservationDAO roomReservationDAO = (RoomReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM_RESERVATION);

    @Override
    public List getStudentIds() throws IOException {

        return studentDAO.getStudentIds();

    }

    @Override
    public List getRoomIds() throws IOException {

        return roomDAO.getRoomIds();
    }

    @Override
    public Student getStudent(String id) throws Exception {
        return studentDAO.find(id);
    }

    @Override
    public Room getRoom(String id) throws Exception {
        return roomDAO.find(id);
    }

    @Override
    public String generateNewReservationID() throws SQLException, ClassNotFoundException, IOException {

        return roomReservationDAO.generateNewId();
    }

    @Override
    public boolean addReservation(RoomReservationDTO reservationDTO) throws Exception {
        return roomReservationDAO.add(new RoomReservation(
                reservationDTO.getRes_id(),
                reservationDTO.getDate(),
                new Student(reservationDTO.getStudent_id()),
                new Room(reservationDTO.getRoom_id()),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public String generateRoomAvailableStatus(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomReservationDAO.generateRoomAvailableStatus(id);
    }


}
