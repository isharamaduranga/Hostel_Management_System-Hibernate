/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/20/2022
 * Time        : 12:03 AM
 * Year        : 2022
 */

package dao.custom.impl;

import dao.custom.RoomReservationDAO;
import entity.RoomReservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDAOImpl implements RoomReservationDAO {

    @Override
    public String generateNewId() throws IOException, SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("SELECT res_id FROM RoomReservation ORDER BY res_id DESC LIMIT 1");
            String id = (String) query.uniqueResult();
            int newCustomerId = Integer.parseInt(id.replace("RID-", "")) + 1;
            transaction.commit();
            return String.format("RID-%03d", newCustomerId);

    }

    @Override
    public boolean add(RoomReservation entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(RoomReservation roomReservation) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public RoomReservation find(String s) throws Exception {
        return null;
    }

    @Override
    public List<RoomReservation> findAll() throws Exception {
        return null;
    }
}
