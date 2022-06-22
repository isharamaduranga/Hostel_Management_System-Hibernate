/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 5:30 PM
 * Year        : 2022
 */

package bo.custom.impl;

import bo.custom.LoginBO;
import dao.custom.UserDAO;
import dao.custom.impl.UserDAOImpl;
import dto.StudentDTO;
import dto.UserDTO;
import entity.User;

import java.util.List;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public User getUserData(String id) throws Exception {
        return  userDAO.find(id);
    }

    @Override
    public List<User> loadAllUser() throws Exception {
       return userDAO.findAll();
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
      return userDAO.update(new User(
                 userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getPassword()
        ));
    }


}
