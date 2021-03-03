package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Создание таблицы User(ов)
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Поликарп","Драбедонтов", (byte) 12);
        userServiceImpl.saveUser("Жиззель","Иванова",(byte) 43);
        userServiceImpl.saveUser("Фёдор","Шульц",(byte) 9);
        userServiceImpl.saveUser("Жанна","Кальман", (byte)122);
        System.out.println("");

        // 3. Получение всех User из базы и вывод в консоль
        List<User> users = userServiceImpl.getAllUsers();
        for (User user :users) {
            System.out.println(user.toString());
        }

        // 4. Очистка таблицы User(ов)
        userServiceImpl.cleanUsersTable();
        // 5. Удаление таблицы
        //userServiceImpl.dropUsersTable();







    }
}
