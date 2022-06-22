package dao.custom;

import entity.CustomEntity;

import java.util.List;

public interface QueryDAO {
    List<CustomEntity> getStudentDetails(String id) throws Exception;
}
