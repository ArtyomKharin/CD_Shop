package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.SupplyDAO;
import net.gui.models.BookingEntity;
import net.gui.models.BookingPositionEntity;
import net.gui.models.SupplyEntity;
import net.gui.utils.HibernateSessionManager;

import java.sql.Date;
import java.util.List;


public class SupplyService implements IService<SupplyEntity> {
    SupplyDAO dao;
    SupplyService(){
        dao=new SupplyDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<SupplyEntity> getAll(){
        return dao.getAll();
    }

    public SupplyEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(SupplyEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }

    public SupplyEntity insert(SupplyEntity object){
        try{
            return dao.insert(object);
        }
        catch (Exception ex){
            error();
            return null;

        }
    }
    public int getAmount(Date date) {
        List<SupplyEntity> list = getAll();
        int amount = 0;
        for (SupplyEntity ent : list) {
            if (ent.getDate().after(date)) {
                    amount += ent.getQuantity();
            }
        }
        return amount;
    }
    public int getTotal(Date date){
        List<SupplyEntity> list = getAll();
        int price = 0;
        for (SupplyEntity ent : list) {
            if (ent.getDate().after(date)) {
                price += ent.getTotalPrice();
            }
        }
        return price;
    }
    public void delete(int id){
        dao.delete(id);
    }
    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again.");
        alert.setContentText("Check provider ID");
        alert.showAndWait();
    }
}
