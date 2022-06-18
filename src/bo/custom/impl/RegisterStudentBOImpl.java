/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/18/2022
 * Time        : 1:13 PM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.RegisterStudentBO;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.StudentDAOImpl;

import java.io.IOException;
import java.util.List;

public class RegisterStudentBOImpl implements RegisterStudentBO {

    @Override
    public List getStudentIds() throws IOException {
        StudentDAO studentDAO = new StudentDAOImpl();
      return studentDAO.getStudentIds();

    }

    @Override
    public List getRoomIds() throws IOException {
        RoomDAO roomDAO = new RoomDAOImpl();
        return roomDAO.getRoomIds();
    }
}