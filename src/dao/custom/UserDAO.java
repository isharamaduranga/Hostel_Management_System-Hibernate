package dao.custom;

import dao.CrudDAO;
import dao.custom.impl.RoomDAOImpl;
import dto.RoomDTO;
import entity.Room;
import entity.Student;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {

}
