package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users_table (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name TEXT NOT NULL," +
                    "lastName TEXT NOT NULL," +
                    "age INT NOT NULL" +
                    ")").executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }


    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }



    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).getResultList();
        } finally {
            sessionFactory.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.crateHibernateSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
