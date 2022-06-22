/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/16/2022
 * Time        : 9:58 PM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.ManageStudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentBOImpl implements ManageStudentBO {
    /**
     * Apply Dependency Injection (Property Injection)
     */
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean add(StudentDTO studentDTO) throws Exception {

        return studentDAO.add(new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(),
                studentDTO.getContact_no(), studentDTO.getDate(), studentDTO.getGender()));
    }

    @Override
    public List<StudentDTO> loadAllStudent() throws Exception {
        List<Student> all = studentDAO.findAll();
        ArrayList<StudentDTO> dtoArrayList = new ArrayList<>();

        for (Student student : all) {
            dtoArrayList.add(new StudentDTO(student.getStudent_id(),
                    student.getName(),
                    student.getAddress(),
                    student.getContact_no(),
                    student.getDate(),
                    student.getGender()));
        }
        return dtoArrayList;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDto) throws Exception {
        return studentDAO.update(new Student(
                studentDto.getStudent_id(),
                studentDto.getName(),
                studentDto.getAddress(),
                studentDto.getContact_no(),
                studentDto.getDate(),
                studentDto.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }
}
