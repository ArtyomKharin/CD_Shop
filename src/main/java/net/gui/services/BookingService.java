package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.BookingDAO;
import net.gui.dao.BookingPositionDAO;
import net.gui.models.BookingEntity;
import net.gui.models.BookingPositionEntity;
import net.gui.utils.HibernateSessionManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class BookingService implements IService<BookingEntity> {
    BookingDAO dao;
    BookingPositionDAO daoPOS;
    BookingService(){
        dao=new BookingDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
        daoPOS=new BookingPositionDAO();
        daoPOS.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<BookingEntity> getAll(){
        return dao.getAll();
    }

    public List<BookingPositionEntity> getBookingPosAll(){
        return daoPOS.getAll();
    }

    public List<BookingPositionEntity> getBookingPosByBookingID(int id){
        if(id!=0) {
            return daoPOS.getByBooking(id);
        }
        else return new ArrayList<>();
    }
    public BookingPositionEntity getBookingPosByComposite(int id,int id2){
        return daoPOS.selectByComposite(id,id2);
    }
    public BookingEntity selectById(int id){
        return dao.selectById(id);
    }

    public void update(BookingEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }
    public void updatePosition(BookingPositionEntity object){
        try{
            daoPOS.update(object);
        }
        catch (Exception ex){}
    }
    public BookingEntity insert(BookingEntity object){
        try{
            return dao.insert(object);
        }
        catch (Exception ex){
            error();
            return null;

        }
    }
    public BookingPositionEntity insertPos(BookingPositionEntity object){
        try{
            return daoPOS.insert(object);
        }
        catch (Exception ex){
            error1();
            return null;

        }
    }
    public int getAmount(Date date) {
        List<BookingEntity> list = getAll();
        int amount = 0;
        for (BookingEntity ent : list) {
            if (ent.getDate().after(date)) {
                for( BookingPositionEntity bPos: getBookingPosByBookingID(ent.getBookingId()))
                        amount += bPos.getQuantity();
            }
        }
        return amount;
    }
    public int getSales(Date date){
        List<BookingEntity> list = getAll();
        int price = 0;
        for (BookingEntity ent : list) {
            if (ent.getDate().after(date)) {
                    price += ent.getTotalPrice();
            }
        }
        return price;
    }
    public void delete(int id){
        dao.delete(id);
    }
    public void deletePosition(BookingPositionEntity entity){
        daoPOS.delete(entity);
    }
    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again");
        alert.setContentText("Check customer ID");
        alert.showAndWait();
    }
    public void error1(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again");
        alert.setContentText("Check consignment ID");
        alert.showAndWait();
    }
}
