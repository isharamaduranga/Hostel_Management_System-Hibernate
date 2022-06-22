package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import dto.UserDTO;
import entity.User;

import java.util.List;

public interface LoginBO extends SuperBO {
    User getUserData(String id) throws Exception;

    List<User> loadAllUser() throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;
}
