/**
 * @author : Ishara Maduarnga
 * Project Name: Hibernate_Cw_Project
 * Date        : 6/16/2022
 * Time        : 8:13 PM
 * Year        : 2022
 */

package util;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;

    private final SessionFactory sessionFactory;

    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        //configuring properties file
        Properties p = new Properties();
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Hibernate.properties"));
        configuration.setProperties(p);

        //configuring entities
        configuration.addAnnotatedClass(Student.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
