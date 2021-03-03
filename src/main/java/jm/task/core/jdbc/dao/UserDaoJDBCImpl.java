package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = Util.connectDB();
    List<User> users = new ArrayList<>();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DROP_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Integer ageInt = Integer.valueOf(age);
        try (PreparedStatement prepareStatement = connection.prepareStatement(SAVE_USER)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, ageInt);
            prepareStatement.execute();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException throwables) {
            System.out.println("При попытке добавить нового пользователя в базу данных произошла ошибка:\n" + throwables);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE_USER)) {
            prepareStatement.setLong(1, id);
            prepareStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Byte age = (byte) resultSet.getInt("age");
                User user = new User(id, name, lastname, age);
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            System.out.println("При попытке создать список пользователей произошла ошибка:\n" + throwables);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CLEAN_TABLE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
