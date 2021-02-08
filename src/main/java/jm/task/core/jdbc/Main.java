package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Создание таблицы User(ов)
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Поликарп","Драбедонтов", (byte) 12);
        userDaoJDBC.saveUser("Жиззель","Иванова",(byte) 43);
        userDaoJDBC.saveUser("Фёдор","Шульц",(byte) 9);
        userDaoJDBC.saveUser("Жанна","Кальман", (byte)122);
        System.out.println("");

        // 3. Получение всех User из базы и вывод в консоль
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user :users) {
            System.out.println(user.toString());
        }
        // 4. Очистка таблицы User(ов)
        userDaoJDBC.cleanUsersTable();
        // 5. Удаление таблицы
        userDaoJDBC.dropUsersTable();







    }
}
