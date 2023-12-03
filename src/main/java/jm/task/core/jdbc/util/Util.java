package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {

    private static final String HOST = "jdbc:mysql://localhost/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private Connection connection;




    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    }

    public static SessionFactory crateHibernateSessionFactory (){
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

    return  configuration.buildSessionFactory();
    }
}