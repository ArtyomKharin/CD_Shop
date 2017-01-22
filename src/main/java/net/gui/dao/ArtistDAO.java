package net.gui.dao;

/**
 * Created by EvSpirit on 20.12.2016.
 */
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import net.gui.models.*;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ArtistDAO implements IDAO<ArtistEntity> {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ArtistEntity> getAll() {
        /*Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.flush();
            Query query = session.createQuery("from ArtistEntity s");

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);

                return (List<ArtistEntity>) queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }*/
        List<ArtistEntity> users = new ArrayList<>();
        Transaction trns = null;
        Session session = sessionFactory.openSession();
        try {
            session.clear();
            trns = session.beginTransaction();
            users = session.createQuery("from ArtistEntity ").list();
            trns.commit();
        } catch (RuntimeException e) {
            error();
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return users;
    }
    public List<ArtistEntity> getAllFiltered(String name,String albums){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            if(albums.equals("")&&name.equals("")) return getAll();
            Query query;
            if(name.equals("")) {
                query = session.createQuery("from ArtistEntity where numberOfAlbums=(:albumsN)");
                query.setParameter("albumsN", Integer.parseInt(albums));
            }
            else{
                if(albums.equals("")) {
                    query = session.createQuery("from ArtistEntity where name like (:aName)");
                    query.setParameter("aName", "%"+name+"%");
                }
                else{
                    query = session.createQuery("from ArtistEntity where name like (:aName) and numberOfAlbums=(:albumsN)");
                    query.setParameter("aName", "%"+name+"%");
                    query.setParameter("albumsN", Integer.parseInt(albums));
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
    public ArtistEntity selectById(int id) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            ArtistEntity label = (ArtistEntity)session.get(ArtistEntity.class, id);
            return label;
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
            //e.printStackTrace();
            return null;
        } finally {
            session.flush();
            session.close();
        }
    }

    public void update(ArtistEntity object) {
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            error();
            System.out.println(e.getMessage());
            //e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public ArtistEntity insert(ArtistEntity object) {
        Session session = null;
        Transaction transaction = null;
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
            //e.printStackTrace();
            return null;
        }
        finally {
            session.flush();
            session.close();
        }
    }

    public void delete(int id){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            //Transaction beginTransaction = session.beginTransaction();
            session.beginTransaction();
            session.delete(selectById(id));
            session.getTransaction().commit();
            //beginTransaction.commit();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            error();

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

