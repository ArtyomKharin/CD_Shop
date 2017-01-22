package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.ConsignmentDAO;
import net.gui.models.ConsignmentEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.List;


public class ConsignmentService implements IService<ConsignmentEntity> {
    ConsignmentDAO dao;
    ConsignmentService(){
        dao=new ConsignmentDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<ConsignmentEntity> getAll(){
        return dao.getAll();
    }

    public ConsignmentEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(ConsignmentEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public ConsignmentEntity insert(ConsignmentEntity object){
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
        alert.setContentText("Check provider ID or CD ID");
        alert.showAndWait();
    }
}
