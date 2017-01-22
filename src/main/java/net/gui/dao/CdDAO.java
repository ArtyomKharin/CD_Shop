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

public class CdDAO implements IDAO<CdEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CdEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from CdEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<CdEntity>) queryList;
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
    public List<CdEntity> getAllFiltered(String genre1,String name){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            if(genre1.equals("")&&name.equals("")) return getAll();
            Query query;
            if(genre1.equals("")) {
                query = session.createQuery("from CdEntity where album Like(:nameCD)");
                query.setParameter("nameCD", "%"+name+"%");
            }
            else{
                if(name.equals("")) {
                    query = session.createQuery("from CdEntity where genre = :genreD");
                    query.setParameter("genreD", genre1);
                }
                else{
                    query = session.createQuery("from CdEntity where genre = :genreD and album Like(:nameCD)");
                    query.setParameter("genreD", genre1);
                    query.setParameter("nameCD",  "%"+name+"%");
                }
            }

            List list = query.list();
            // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if(!list.isEmpty())return  list;
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

    public CdEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            CdEntity label = (CdEntity)session.get(CdEntity.class, id);
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

    public void update(CdEntity object) {
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

    public CdEntity insert(CdEntity object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            System.out.println("session : "+session);
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
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
