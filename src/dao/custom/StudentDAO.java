package dao.custom;

import dao.CrudDAO;
import entity.Student;

import java.io.IOException;
import java.util.List;


public interface StudentDAO extends CrudDAO<Student,String> {
    List getStudentIds() throws IOException;
}

