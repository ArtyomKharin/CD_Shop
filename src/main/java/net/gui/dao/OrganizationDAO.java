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

public class OrganizationDAO implements IDAO<OrganizationEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<OrganizationEntity> getAll() {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from OrganizationEntity s");

            List queryList = query.list();
            session.getTransaction().commit();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<OrganizationEntity>) queryList;
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

    public OrganizationEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            OrganizationEntity label = (OrganizationEntity)session.get(OrganizationEntity.class, id);
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

    public void update(OrganizationEntity object) {
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
    public int selectByFilter(String iname,String phoneNum,String email){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            if(iname.equals("")||phoneNum.equals("")||email.equals("")) return -1;
            Query query = session.createQuery("from OrganizationEntity where nameOfOrganization Like(:name) and mail Like(:email) and phone Like(:phNumber)");
            query.setParameter("name",  iname);
            query.setParameter("email",  email);
            query.setParameter("phNumber",  phoneNum);

            List list = query.list();
            // UserEntity label = (UserEntity)session.get(UserEntity.class, id);
            if(!list.isEmpty()){
                OrganizationEntity temp= (OrganizationEntity)list.get(0);
                return  temp.getOrganizationId();
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
    public OrganizationEntity insert(OrganizationEntity object) {
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

