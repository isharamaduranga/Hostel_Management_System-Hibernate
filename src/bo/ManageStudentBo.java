package bo;

import dto.StudentDTO;

public interface ManageStudentBo {
    boolean add(StudentDTO studentDTO) throws Exception;
}
