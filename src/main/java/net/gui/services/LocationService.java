package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.LocationDAO;
import net.gui.models.LocationEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.List;


public class LocationService implements IService<LocationEntity> {
    LocationDAO dao;
    LocationService(){
        dao=new LocationDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<LocationEntity> getAll(){
        return dao.getAll();
    }

    public LocationEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(LocationEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public LocationEntity insert(LocationEntity object){
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
        alert.setContentText("Check input data.");
        alert.showAndWait();
    }
}
