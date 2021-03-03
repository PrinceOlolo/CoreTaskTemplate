package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/testbase";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static SessionFactory connectHDB() {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.dialect", DIALECT);
            properties.setProperty("hibernate.connection.driver_class", DRIVER);
            properties.setProperty("hibernate.connection.username", LOGIN);
            properties.setProperty("hibernate.connection.password", PASSWORD);
            properties.setProperty("hibernate.connection.url", URL);
            properties.setProperty("hibernate.c3p0.min_size", "5");
            properties.setProperty("hibernate.c3p0.max_size", "200");
            properties.setProperty("hibernate.c3p0.max_statements", "200");
            properties.setProperty("show_sql", "true");
            properties.setProperty("hbm2ddl.auto", "update");

            sessionFactory = new Configuration().addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("При подключения к базе данных с помощью Hibernate, произошла ошибка:\n" + e);
        }
        return sessionFactory;
    }

    public static Connection connectDB() {
        try {
            Class.forName(DRIVER);

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("При загрузке драйвера произошла ошибка:\n" + e);
        } catch (SQLException e) {
            System.out.println("При подключения к базе данных с помощью JDBC, произошла ошибка:\n" + e);
        }
        return connection;
    }
}


