package net.gui.services;
/**
 * Created by EvSpirit on 20.12.2016.
 */
import javafx.scene.control.Alert;
import net.gui.dao.*;
import net.gui.models.*;
import net.gui.utils.HibernateSessionManager;

import java.util.List;


public class OrganizationsService implements IService<OrganizationEntity> {
    OrganizationDAO dao;
    MusicLabelDAO mldao;
    ProviderDAO prdao;
    OrganizationsService(){
        dao=new OrganizationDAO();
        dao.setSessionFactory(HibernateSessionManager.getSessionFactory());
        mldao=new MusicLabelDAO();
        mldao.setSessionFactory(HibernateSessionManager.getSessionFactory());
        prdao=new ProviderDAO();
        prdao.setSessionFactory(HibernateSessionManager.getSessionFactory());
    }
    public List<OrganizationEntity> getAll(){
        return dao.getAll();
    }
    public List<MusicLabelEntity> getAllMl(){
        return mldao.getAll();
    }
    public List<ProviderEntity> getAllPr(){
        return prdao.getAll();
    }
/*
    public List<OrganizationEntity> getOrganizationPosAll(){
        return daoPOS.getAll();
    }

    public List<OrganizationPositionEntity> getOrganizationPosByOrganizationID(int id){
        return daoPOS.getByOrganization(id);
    }
*/
    public OrganizationEntity selectById(int id){
        return dao.selectById(id);
    }
    public MusicLabelEntity selectByIdML(int id){
        return mldao.selectById(id);
    }
    public ProviderEntity selectByIdPr(int id){
        return prdao.selectById(id);
    }
    public void update(OrganizationEntity object){
        try{
            dao.update(object);
        }
        catch (Exception ex){}
    }
    public void updateML(MusicLabelEntity object){
        try{
            mldao.update(object);
        }
        catch (Exception ex){}
    }
    public void updatePr(ProviderEntity object){
        try{
            prdao.update(object);
        }
        catch (Exception ex){}
    }
    public OrganizationEntity insert(OrganizationEntity object){
        try{
            return dao.insert(object);
        }
        catch (Exception ex){
            error();
            return null;

        }
    }
    public int selectByFilter(String iname,String phoneNum,String email){
        return dao.selectByFilter(iname,phoneNum,email);
    }
    public MusicLabelEntity insertML(MusicLabelEntity object){
        try{
            return mldao.insert(object);
        }
        catch (Exception ex){
            error1();
            return null;

        }
    }
    public ProviderEntity insertPr(ProviderEntity object)throws Exception{
        try{
            return prdao.insert(object);
        }
        catch (Exception ex){
            error1();
            return null;

        }
    }

    public void delete(int id){
        dao.delete(id);
    }
    public void deleteLabel(int id){
        mldao.delete(id);
    }
    public void deleteProvider(int id){
        prdao.delete(id);
    }
    public void error(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again.");
        alert.setContentText("Check input data.");
        alert.showAndWait();
    }
    public void error1(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong data, try again.");
        alert.setContentText("Check organization ID.");
        alert.showAndWait();
    }
}
