package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.ArtistDAO;
import net.gui.models.ArtistEntity;
import net.gui.models.CdEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.ArrayList;
import java.util.List;


public class ArtistService implements IService<ArtistEntity> {
    ArtistDAO dao;
    ArtistService(){
        dao=new ArtistDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<ArtistEntity> getAll(){
        return dao.getAll();
    }
    public List<ArtistEntity> getAllFiltered(String name, String albums){
        List list=dao.getAllFiltered(name,albums);
        if(list==null){
            return new ArrayList<ArtistEntity>();
        }
        else{
            return list;
        }
    }
    public ArtistEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(ArtistEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public ArtistEntity insert  (ArtistEntity object){
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
        alert.setContentText("Check input data");
        alert.showAndWait();
    }
}
