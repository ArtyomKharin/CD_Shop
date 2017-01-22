package net.gui.dao;

/**
 * Created by EvSpirit on 20.12.2016.
 */
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import net.gui.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BookingPositionDAO implements IDAO<BookingPositionEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<BookingPositionEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from BookingPositionEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<BookingPositionEntity>) queryList;
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

    public List<BookingPositionEntity> getByBooking(int id) {
        Session session = null;
        try
        {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from BookingPositionEntity where bookingId = :id");
            query.setParameter("id", id);
            List list = query.list();
            session.getTransaction().commit();
            // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if (!list.isEmpty()) return list;
            //return user.getUserId();
        } catch (
                Exception e)

        {
            error();
            System.out.println(e.getMessage());
        } finally

        {
            session.flush();
            session.close();
        }
        return new ArrayList<BookingPositionEntity>();
    }
    public BookingPositionEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            BookingPositionEntity label = (BookingPositionEntity)session.get(BookingPositionEntity.class, id);
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
    public BookingPositionEntity selectByComposite(int id,int cusID) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Query query = session.createQuery("from BookingPositionEntity where bookingId = :bID and consignmentId = :cID");
            query.setParameter("bID", id);
            query.setParameter("cID", cusID);
            List list = query.list();
            // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if(!list.isEmpty())return (BookingPositionEntity) list.get(0);
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


    public void update(BookingPositionEntity object) {
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

    public BookingPositionEntity insert(BookingPositionEntity object) {
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
            session.delete(getByBooking(id));
            beginTransaction.commit();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }
    public void delete(BookingPositionEntity entity){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            session.delete(entity);
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
