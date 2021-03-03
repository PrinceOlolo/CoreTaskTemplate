package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory = Util.connectHDB();
    private static Session session;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE from users WHERE id = :id ")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            return session.createQuery("SELECT a FROM User a ", User.class).getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        finally {
            session.close();
        }
        return null;
    }


    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            session.close();
        }

    }
}
