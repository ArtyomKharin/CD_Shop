package net.gui.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.gui.models.*;
import net.gui.services.ServiceList;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by EvSpirit on 12.01.2017.
 */
public class ChoosingController implements Initializable {
    @FXML
    private TableView<ArtistEntity> artistTable;
    @FXML
    private TableView<CdEntity> cdTable;
    @FXML
    private TableView<ConsignmentEntity> consignmentTable;
    @FXML
    private TableView<LocationEntity> locationTable;
    @FXML
    private TableView<MusicLabelEntity> labelTable;
    @FXML
    private TableView<ProviderEntity> providerTable;
    @FXML
    private Button addButton;
    @FXML
    private Button chooseButton;
    public static String mode="";
    public static String calledBy="";
    public ChoosingController(){}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeArtist();
        initializeCD();
        initializeConsignment();
        initializeLocation();
        initializeProvider();
        initializeLabel();
        hideAll();
        switch (mode) {
            case ("Artist"):
                artistTable.setVisible(true);
                refreshArtist();
                break;
            case ("CD"):
                cdTable.setVisible(true);
                refreshCd();
                break;
            case ("Consignment"):
                consignmentTable.setVisible(true);
                refreshConsignment();
                break;
            case ("Location"):
                locationTable.setVisible(true);
                refreshLocation();
                break;
            case ("Music Label"):
                labelTable.setVisible(true);
                refreshLabel();
                break;
            case ("Provider"):
                providerTable.setVisible(true);
                refreshProvider();
                break;
        }
    }
    private void hideAll(){
        artistTable.setVisible(false);
        cdTable.setVisible(false);
        consignmentTable.setVisible(false);
        locationTable.setVisible(false);
        labelTable.setVisible(false);
        providerTable.setVisible(false);
    }
    public void initializeArtist() {
        MainController.initializeArtist(artistTable);
    }

    public void initializeCD(){
        TableColumn<CdEntity, String> albumColumn = new TableColumn<>("Album");
        albumColumn.setMinWidth(155);
        albumColumn.setCellValueFactory(
                new PropertyValueFactory<>("album"));
        TableColumn<CdEntity, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(178);
        genreColumn.setCellValueFactory(
                new PropertyValueFactory<>("genre"));
        TableColumn<CdEntity, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(162);
        artistColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getArtistByArtistId().getName()));

        TableColumn<CdEntity, String> labelColumn = new TableColumn<>("Music Label");
        labelColumn.setMinWidth(167);
        labelColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getMusicLabelByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization()));

        cdTable.getColumns().clear();
        cdTable.getColumns().add(albumColumn);
        cdTable.getColumns().add(genreColumn);
        cdTable.getColumns().add(artistColumn);
        cdTable.getColumns().add(labelColumn);
        //firstChoice.getItems().addAll(FXCollections.observableArrayList("Blues","Jazz","Country","Chanson","Electronic music","Rock","Pop","Rap/Hip-hop"));


    }
    public void initializeConsignment(){
        MainController.initializeConsignment(consignmentTable);

    }
    public void initializeLocation(){
        MainController.initializeLocation(locationTable);
    }

    public void initializeProvider(){
        //provider table
        TableColumn<ProviderEntity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(186);
        nameColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getNameOfOrganization()));

        TableColumn<ProviderEntity, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setMinWidth(143);
        phoneColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getPhone()));

        TableColumn<ProviderEntity, String> mailColumn = new TableColumn<>("E-mail");
        mailColumn.setMinWidth(225);
        mailColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getMail()));

        TableColumn<ProviderEntity, String> itnColumn = new TableColumn<>("ITN");
        itnColumn.setMinWidth(175);
        itnColumn.setCellValueFactory(
                new PropertyValueFactory<>("itn"));
        providerTable.getColumns().clear();
        providerTable.getColumns().add(nameColumn);
        providerTable.getColumns().add(phoneColumn);
        providerTable.getColumns().add(mailColumn);
        providerTable.getColumns().add(itnColumn);

    }
    public void initializeLabel(){
        //music label table
        TableColumn<MusicLabelEntity, String> nameColumn1 = new TableColumn<>("Name");
        nameColumn1.setMinWidth(186);
        nameColumn1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getNameOfOrganization()));

        TableColumn<MusicLabelEntity, String> phoneColumn1 = new TableColumn<>("Phone");
        phoneColumn1.setMinWidth(143);
        phoneColumn1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getPhone()));

        TableColumn<MusicLabelEntity, String> mailColumn1 = new TableColumn<>("E-mail");
        mailColumn1.setMinWidth(225);
        mailColumn1.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getOrganizationByOrganizationId().getMail()));

        TableColumn<MusicLabelEntity, Integer> studioColumn = new TableColumn<>("Amount of record studios");
        studioColumn.setMinWidth(175);
        studioColumn.setCellValueFactory(
                new PropertyValueFactory<>("studioAmount"));
        labelTable.getColumns().clear();
        labelTable.getColumns().add(nameColumn1);
        labelTable.getColumns().add(phoneColumn1);
        labelTable.getColumns().add(mailColumn1);
        labelTable.getColumns().add(studioColumn);
    }
    private void refreshArtist(){
        ServiceList.refresh();
        ObservableList<ArtistEntity> list =FXCollections.observableList(ServiceList.artistService.getAll());
        artistTable.getItems().clear();
        artistTable.setItems(list);
    }
    private void refreshCd(){
        ServiceList.refresh();
        ObservableList<CdEntity> list4 =FXCollections.observableList(ServiceList.cdService.getAll());
        cdTable.getItems().clear();
        cdTable.setItems(list4);
    }
    private void refreshConsignment(){
        ServiceList.refresh();
        ObservableList<ConsignmentEntity> list5 =FXCollections.observableList(ServiceList.consignmentService.getAll());
        consignmentTable.getItems().clear();
        consignmentTable.setItems(list5);
    }
    private void refreshLocation(){
        ServiceList.refresh();
        ObservableList<LocationEntity> list7 =FXCollections.observableList(ServiceList.locationService.getAll());
        locationTable.getItems().clear();
        locationTable.setItems(list7);
    }
    private void refreshProvider(){
        ServiceList.refresh();
        ObservableList<ProviderEntity> list10 =FXCollections.observableList(ServiceList.organizationsService.getAllPr());
        providerTable.getItems().clear();
        providerTable.setItems(list10);
    }
    private void refreshLabel(){
        ServiceList.refresh();
        ObservableList<MusicLabelEntity> list9 =FXCollections.observableList(ServiceList.organizationsService.getAllMl());
        labelTable.getItems().clear();
        labelTable.setItems(list9);
    }
    public void choose(ActionEvent e){
        switch (mode){
            case ("Artist"):
                if(artistTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.artID=artistTable.getSelectionModel().getSelectedItem().getArtistId();
                        cancel();
                    }
                    else{
                        EditController.artID=artistTable.getSelectionModel().getSelectedItem().getArtistId();
                        cancel();
                    }
                }
                break;
            case ("CD"):
                if(cdTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.cdID=cdTable.getSelectionModel().getSelectedItem().getCdId();
                        cancel();
                    }
                    else{
                        EditController.cdID=cdTable.getSelectionModel().getSelectedItem().getCdId();
                        cancel();
                    }
                }
                break;
            case ("Consignment"):
                if(consignmentTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.consID=consignmentTable.getSelectionModel().getSelectedItem().getConsignmentId();
                        cancel();
                    }
                    else{
                        EditController.consID=consignmentTable.getSelectionModel().getSelectedItem().getConsignmentId();
                        cancel();
                    }
                }
                break;
            case ("Location"):
                if(locationTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.locID=locationTable.getSelectionModel().getSelectedItem().getLocationId();
                        cancel();
                    }
                    else{
                        EditController.locID=locationTable.getSelectionModel().getSelectedItem().getLocationId();
                        cancel();
                    }
                }
                break;
            case ("Music Label"):
                if(labelTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.labelID=labelTable.getSelectionModel().getSelectedItem().getOrganizationId();
                        cancel();
                    }
                    else{
                        EditController.labelID=labelTable.getSelectionModel().getSelectedItem().getOrganizationId();
                        cancel();
                    }
                }
                break;
            case ("Provider"):
                if(providerTable.getSelectionModel().getSelectedItem()!=null){
                    if(calledBy.equals("Add")) {
                        AddingController.prID=providerTable.getSelectionModel().getSelectedItem().getOrganizationId();
                        cancel();
                    }
                    else{
                        EditController.prID=providerTable.getSelectionModel().getSelectedItem().getOrganizationId();
                        cancel();
                    }
                }
                break;
        }
    }
    public void cancel(){
        Stage stage = (Stage) chooseButton.getScene().getWindow();
        stage.close();
    }
}
