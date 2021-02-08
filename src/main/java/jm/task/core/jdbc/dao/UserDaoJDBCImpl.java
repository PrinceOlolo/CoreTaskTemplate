package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String SAVE_USER = "INSERT INTO users(name,lastname,age)" + " VALUES(?,?,?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    public static final String CLEAN_TABLE = "DELETE FROM users";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `testbase`.`users` (\n" +
            "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastname` VARCHAR(45) NOT NULL,\n" +
            "  `age` INT(3) UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";

    Statement statement = null;
    PreparedStatement prepareStatement = null;
    List<User> users = new ArrayList<>();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement = Util.connectDB().createStatement();
            statement.execute(CREATE_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                System.out.println("Ошибка закрытия Соединения с базой данных!");
            }
            Util.disconnectDB();
        }
    }

    public void dropUsersTable() {
        try {
            statement = Util.connectDB().createStatement();
            statement.execute(DROP_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                System.out.println("Ошибка закрытия Соединения с базой данных!");
            }
            Util.disconnectDB();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
       Integer ageInt = Integer.valueOf(age);
        try {
            prepareStatement = Util.connectDB().prepareStatement(SAVE_USER);
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, ageInt);
            prepareStatement.execute();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);

        } catch (SQLException throwables) {
            System.out.println("При попытке добавить нового пользователя в базу данных произошла ошибка:\n"+ throwables);
        } finally {
            try {
                prepareStatement.close();
            } catch (SQLException throwables) {
                System.out.println("Ошибка закрытия Соединения с базой данных!");
            }
            Util.disconnectDB();
        }
    }

    public void removeUserById(long id) {
        try {
            prepareStatement = Util.connectDB().prepareStatement(DELETE_USER);
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                prepareStatement.close();
            } catch (SQLException throwables) {
                System.out.println("Ошибка закрытия Соединения с базой данных!");
            }
            Util.disconnectDB();
        }

    }

    public List<User> getAllUsers() {
        try {
            statement = Util.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Byte age = (byte) resultSet.getInt("age");
                User user = new User(id, name, lastname, age);
                users.add(user);
            }
            statement.close();
            Util.disconnectDB();
            return users;
        } catch (SQLException throwables) {
            System.out.println("При попытке создать список пользователей произошла ошибка:\n" + throwables);
        } finally {
         Util.disconnectDB();
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            statement = Util.connectDB().createStatement();
            statement.execute(CLEAN_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                System.out.println("Ошибка закрытия Соединения с базой данных!");
            }
            Util.disconnectDB();
        }
    }
}
