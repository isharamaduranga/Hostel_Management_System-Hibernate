package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getStudentDetails(String id) throws Exception;
}
