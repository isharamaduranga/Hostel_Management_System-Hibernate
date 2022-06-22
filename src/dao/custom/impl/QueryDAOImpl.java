/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 11:18 AM
 * Year        : 2022
 */

package dao.custom.impl;

import dao.custom.QueryDAO;
import entity.CustomEntity;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getStudentDetails(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql= "SELECT s.student_id , s.name, r.date FROM Student s INNER JOIN RoomReservation r ON s.student_id = r.student WHERE r.room=:room";
        Query query = session.createQuery(hql);
        query.setParameter("room",session.load(Room.class,id));
        List<Object[]>list= query.list();
        List<CustomEntity> allDetails= new ArrayList<>();
        for (Object[] objects : list) {
            allDetails.add(new CustomEntity(objects[0].toString(),objects[1].toString(), LocalDate.parse(objects[2].toString())));
        }
        return allDetails;
    }
}
