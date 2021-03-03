package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl us = new UserDaoJDBCImpl();
    UserDaoHibernateImpl uh = new UserDaoHibernateImpl();

    public void createUsersTable() {
        uh.createUsersTable();
    }

    public void dropUsersTable() { uh.dropUsersTable(); }

    public void saveUser(String name, String lastName, byte age) {
        uh.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) { uh.removeUserById(id); }

    public List<User> getAllUsers() {
        return uh.getAllUsers();
    }

    public void cleanUsersTable() {
        uh.cleanUsersTable();
    }
}
