/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/16/2022
 * Time        : 9:58 PM
 * Year        : 2022
 */

package bo;

import dao.custom.StudentDAO;
import dao.custom.impl.StudentDAOImpl;
import dto.StudentDTO;
import entity.Student;

public class ManageStudentBOImpl implements ManageStudentBo {

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {
         StudentDAO studentDAO = new StudentDAOImpl();
      return studentDAO.add(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),
                 studentDTO.getContact_no(),studentDTO.getDate(),studentDTO.getGender()));
    }
}
