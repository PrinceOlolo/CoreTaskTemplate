package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        return us.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        us.cleanUsersTable();
    }
}
