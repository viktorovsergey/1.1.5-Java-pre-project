package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        testService(new UserServiceImpl());


    }

    public static void testService(UserService userService) {

        userService.createUsersTable();

        userService.saveUser("Мария", "Шереметьевская", (byte) 23);
        userService.saveUser("Денис", "Иванов", (byte) 25);
        userService.saveUser("Михаил", "Гальвас", (byte) 23);
        userService.saveUser("Владик", "Зайцев", (byte) 24);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}