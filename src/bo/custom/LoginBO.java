package bo.custom;

import dto.StudentDTO;
import dto.UserDTO;
import entity.User;

import java.util.List;

public interface LoginBO {
    User getUserData(String id) throws Exception;

    List<User> loadAllUser() throws Exception;

    boolean updateUser(UserDTO userDTO) throws Exception;
}
