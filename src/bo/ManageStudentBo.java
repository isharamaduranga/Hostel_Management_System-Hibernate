package bo;

import dto.StudentDTO;

import java.util.List;

public interface ManageStudentBo {
    boolean add(StudentDTO studentDTO) throws Exception;
    List<StudentDTO> loadAllStudent() throws Exception;
    boolean updateStudent(StudentDTO studentDto) throws Exception;
    boolean deleteStudent(String id)throws Exception;

}
