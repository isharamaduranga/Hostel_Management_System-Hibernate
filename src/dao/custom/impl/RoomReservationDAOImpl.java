/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/20/2022
 * Time        : 12:03 AM
 * Year        : 2022
 */

package dao.custom.impl;

import dao.custom.RoomReservationDAO;
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
        if (query.isCallable()) {
            String rnno = String.valueOf(query);
            int co = rnno.length();
            String txt = rnno.substring(0, 2);//mul deka  (RI)
            String num = rnno.substring(2, co);//aga deaka (1000)

            int n = Integer.parseInt(num);
            n++;
            String snum = Integer.toString(n);
            String newID = txt + snum;
            transaction.commit();
            session.close();
            return newID;
        } else {
            return "RI1000";
        }
    }
}
