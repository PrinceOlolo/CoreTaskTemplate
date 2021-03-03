package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String SAVE_USER = "INSERT INTO users(name,lastname,age)" + " VALUES(?,?,?)";
    String GET_ALL_USERS = "SELECT * FROM users";
    String DELETE_USER = "DELETE FROM users WHERE id=?";
    String CLEAN_TABLE = "DELETE FROM users";
    String DROP_TABLE = "DROP TABLE IF EXISTS users";
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS `testbase`.`users` (\n" +
            "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `lastname` VARCHAR(45) NOT NULL,\n" +
            "  `age` INT(3) UNSIGNED NOT NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
