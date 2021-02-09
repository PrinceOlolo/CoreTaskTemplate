package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/testbase";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("При загрузке драйвера произошла ошибка:\n" + e);
        } catch (SQLException e) {
            System.out.println("При подключения к базе данных произошла ошибка:\n" + e);
        }
        return connection;
    }

}


