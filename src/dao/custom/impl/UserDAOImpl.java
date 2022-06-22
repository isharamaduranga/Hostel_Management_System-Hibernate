/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/22/2022
 * Time        : 5:55 PM
 * Year        : 2022
 */

package dao.custom.impl;

import dao.custom.UserDAO;
import entity.Room;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean add(User user) throws Exception {
        return false;
    }

    @Override
    public boolean update(User user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String uid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, uid);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = null;
        Query sessionQuery = session.createQuery("from User");
        userList = sessionQuery.list();
        transaction.commit();
        session.close();
        return userList;
    }
}
