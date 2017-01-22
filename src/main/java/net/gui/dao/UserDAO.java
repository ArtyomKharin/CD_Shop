package net.gui.dao;

import javafx.scene.control.Alert;
import net.gui.models.UserEntity;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by EvSpirit on 20.12.2016.
 */
public class UserDAO implements IDAO<UserEntity>{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<UserEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<UserEntity>) queryList;
            }
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    public UserEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            return label;
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    public UserEntity verify(String name, String pass) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("from UserEntity where login = :name and password = :pass");
            query.setParameter("name", name);
            query.setParameter("pass", pass);
            List list = query.list();
           // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if(!list.isEmpty())return (UserEntity)list.get(0);
            //return user.getUserId();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }

    public void update(UserEntity object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public UserEntity insert(UserEntity object) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            System.out.println("session : "+session);
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            return object;
        } catch (Exception e) {
            //error();
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            session.flush();
            session.close();
        }
    }

    public void delete(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            session.delete(selectById(id));
            beginTransaction.commit();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }
    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again");
        alert.setContentText("Check input data");
        alert.showAndWait();
    }
}
