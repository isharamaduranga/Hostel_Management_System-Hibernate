package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface ManageStudentBO extends SuperBO {
    boolean add(StudentDTO studentDTO) throws Exception;
    List<StudentDTO> loadAllStudent() throws Exception;
    boolean updateStudent(StudentDTO studentDto) throws Exception;
    boolean deleteStudent(String id)throws Exception;

}
