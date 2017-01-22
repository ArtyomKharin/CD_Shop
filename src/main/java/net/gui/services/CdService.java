package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.CdDAO;
import net.gui.models.CdEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.ArrayList;
import java.util.List;


public class CdService implements IService<CdEntity> {
    CdDAO dao;
    CdService(){
        dao=new CdDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<CdEntity> getAll(){
        return dao.getAll();
    }
    public List<CdEntity> getAllFiltered(String genre, String id){
        List list=dao.getAllFiltered(genre,id);
        if(list==null){
            return new ArrayList<CdEntity>();
        }
        else{
            return list;
        }
    }

    public CdEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(CdEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public CdEntity insert(CdEntity object){
        try{
            return dao.insert(object);
        }
        catch (Exception ex){
            error();
            return null;

        }
    }

    public void delete(int id){
        dao.delete(id);
    }
    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again");
        alert.setContentText("Check artist ID and Music Label ID");
        alert.showAndWait();
    }
}
