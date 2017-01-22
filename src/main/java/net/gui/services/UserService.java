package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.controller.MainController;
import net.gui.dao.UserDAO;
import net.gui.models.UserEntity;
import net.gui.models.UserEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.ArrayList;
import java.util.List;


public class UserService implements IService<UserEntity> {
    private UserDAO dao;
    public UserDAO getDao() {
        return dao;
    }

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }
    public UserService(){
        dao=new UserDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public boolean Authorization(String login, String pass){
        UserEntity user=dao.verify(login,pass);
        if(user!=null){
            if(user.isAdminMode()) MainController.adminMode=true;
            else MainController.adminMode=false;
            MainController.name=user.getLogin();
            return true;
        }
        return false;
    }
    public List<UserEntity> getAll(){
        return dao.getAll();
    }

    public UserEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(UserEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public UserEntity insert(UserEntity object){
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
        alert.setHeaderText("Wrong data, try again.");
        alert.setContentText("Check input data");
        alert.showAndWait();
    }
}
