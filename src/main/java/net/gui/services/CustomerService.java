package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.CustomerDAO;
import net.gui.models.CdEntity;
import net.gui.models.CustomerEntity;
import net.gui.utils.HibernateSessionManager;

import java.util.ArrayList;
import java.util.List;


public class CustomerService implements IService<CustomerEntity> {
    CustomerDAO dao;
    CustomerService(){
        dao=new CustomerDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<CustomerEntity> getAll(){
        return dao.getAll();
    }

    public CustomerEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(CustomerEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }
    public List<CustomerEntity> getAllFiltered(String name, String lname){
        List list=dao.getAllFiltered(name,lname);
        if(list==null){
            return new ArrayList<CustomerEntity>();
        }
        else{
            return list;
        }
    }
    public int selectByName(String name, String lname, String phone){
        return dao.selectByName(name,lname,phone);
    }
    public CustomerEntity insert(CustomerEntity object){
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
        alert.setContentText("Check location ID");
        alert.showAndWait();
    }
}
