package net.gui.dao;

/**
 * Created by EvSpirit on 20.12.2016.
 */
import java.util.List;

import javafx.scene.control.Alert;
import net.gui.models.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SupplyDAO implements IDAO<SupplyEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<SupplyEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from SupplyEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<SupplyEntity>) queryList;
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

    public SupplyEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            SupplyEntity label = (SupplyEntity)session.get(SupplyEntity.class, id);
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

    public void update(SupplyEntity object) {
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

    public SupplyEntity insert(SupplyEntity object) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = this.sessionFactory.openSession();
            System.out.println("session  : "+session);
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
            System.out.print("");
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

