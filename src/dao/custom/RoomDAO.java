package dao.custom;

import dao.CrudDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {

     List getRoomIds() throws IOException;
}
