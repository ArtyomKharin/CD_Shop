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

public class CustomerDAO implements IDAO<CustomerEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CustomerEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from CustomerEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<CustomerEntity>) queryList;
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
    public List<CustomerEntity> getAllFiltered(String iname,String ilname){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            if(iname.equals("")&&ilname.equals("")) return getAll();
            Query query;
            if(ilname.equals("")) {
                query = session.createQuery("from CustomerEntity where name Like(:name1)");
                query.setParameter("name1",  "%"+iname+"%");
            }
            else{
                if(iname.equals("")) {
                    query = session.createQuery("from CustomerEntity where surname Like(:name2)");
                    query.setParameter("name2",  "%"+ilname+"%");
                }
                else{
                    query = session.createQuery("from CustomerEntity where name Like(:name1) and surname Like(:name2)");
                    query.setParameter("name2",  "%"+ilname+"%");
                    query.setParameter("name1",  "%"+iname+"%");
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
    public int selectByName(String iname,String ilname,String number){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            if(iname.equals("")||ilname.equals("")||number.equals("")) return -1;
            Query query = session.createQuery("from CustomerEntity where name Like(:name1) and surname Like(:name2) and phone Like(:phNumber)");
            query.setParameter("name2",  "%"+ilname+"%");
            query.setParameter("name1",  "%"+iname+"%");
            query.setParameter("phNumber",  "%"+number+"%");

            List list = query.list();
            // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if(!list.isEmpty()){
                CustomerEntity temp= (CustomerEntity)list.get(0);
                return  temp.getCustomerId();
            }
            //return user.getUserId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return -1;
    }
    public CustomerEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            CustomerEntity label = (CustomerEntity)session.get(CustomerEntity.class, id);
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

    public void update(CustomerEntity object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            //session.getTransaction().commit();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
    }

    public CustomerEntity insert(CustomerEntity object) {
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
      //      error();
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

