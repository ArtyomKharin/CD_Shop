package net.gui.dao;

/**
 * Created by EvSpirit on 20.12.2016.
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import net.gui.models.*;
import net.gui.services.ServiceList;
import org.hibernate.*;

public class BookingDAO implements IDAO<BookingEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<BookingEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from BookingEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<BookingEntity>) queryList;
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


    public BookingEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            BookingEntity label = (BookingEntity)session.get(BookingEntity.class, id);
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

    public void update(BookingEntity object) {
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

    public BookingEntity insert(BookingEntity object) {
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