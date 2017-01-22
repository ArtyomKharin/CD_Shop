package net.gui.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import net.gui.dao.MusicLabelDAO;
import net.gui.models.*;
import net.gui.services.ArtistService;
import net.gui.services.ServiceList;
import net.gui.utils.HibernateSessionManager;
import net.gui.ModalWindow;

import java.io.IOException;
import java.sql.Date;

import static java.lang.System.exit;

/**
 * Created by EvSpirit on 14.12.2016.
 */
public class MainController {
    public static boolean adminMode=false;
    @FXML
    private Label aNameLabel;
    @FXML
    private Label albumsLabel;
    @FXML
    private Button deleteMLButton;
    @FXML
    private Button deletePrButton;
    @FXML
    private Button addPosButton;
    @FXML
    private Button editPosButton;
    public static String name;
    @FXML
    private Button deletePositionButton;
    public static String mode;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button changeButton;
    @FXML
    private Button switchButton;
    @FXML
    private Button setPaidButton;
    @FXML
    private Button setNotPaidButton;
    @FXML
    private Label userLabel;
    @FXML
    private ChoiceBox<String> switchBox;
    @FXML
    private TableView<ArtistEntity> artistTable;
    @FXML
    private TableView<BookingEntity> bookingTable;
    @FXML
    private TableView<BookingPositionEntity> bookingPositionTable;
    @FXML
    private TableView<CdEntity> cdTable;
    @FXML
    private TableView<ConsignmentEntity> consignmentTable;
    @FXML
    private TableView<CustomerEntity> customerTable;
    @FXML
    private TableView<LocationEntity> locationTable;
    @FXML
    private TableView<MusicLabelEntity> labelTable;
    @FXML
    private TableView<ProviderEntity> providerTable;
    @FXML
    private TableView<SupplyEntity> supplyTable;
    @FXML
    private TableView<UserEntity> userTable;
    @FXML
    private Button addPrButton;
    @FXML
    private Button addMLButton;
    @FXML
    private Button editPrButton;
    @FXML
    private Button editMLButton;
    @FXML
    private Label countryLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label postLabel;
    @FXML
    private Button giveAdminButton;
    @FXML
    private Button takeAdminButton;
    @FXML
    private Button filterButton;
    @FXML
    private Label artistLabel1;
    @FXML
    private Label genreLabel;
    @FXML
    private ChoiceBox<String> firstChoice;
    @FXML
    private ChoiceBox<String> secondChoice;
    @FXML
    private TextField firstTextField;
    @FXML
    private TextField secondTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label lnameLabel;
    @FXML
    private Label statisticsLabel;
    @FXML
    private Label weekLabel;
    @FXML
    private Label monthLabel;
    @FXML
    private Label amountWeekLabel;
    @FXML
    private Label amountMonthLabel;
    @FXML
    private Label saleWeekSumLabel;
    @FXML
    private Label saleMonthSumLabel;
    @FXML
    private Label disksWeekLabel;
    @FXML
    private Label disksMonthLabel;
    @FXML
    private Label supplyWeekLabel;
    @FXML
    private Label supplyMonthLabel;
    @FXML
    private TabPane organizationsTabPane;
    public void initialize(){

        try {
            ModalWindow modal=new ModalWindow();
            modal.newWindow("login","","");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if(name==null)exit(0);
        //mlDAO.setSessionFactory(HibernateSessionManager.getSessionFactory());
        if(adminMode) {
            switchBox.setItems(FXCollections.observableArrayList("Artist", "Booking", "CD", "Consignment", "Customer", "Location",
                    "Organizations", "Supply", "User"));
        }
        else{
            switchBox.setItems(FXCollections.observableArrayList("Artist", "Booking", "CD", "Consignment", "Customer", "Location",
                    "Organizations", "Supply"));
        }
        userLabel.setText(name);
        showStatistics(new ActionEvent());
        //hideAll();
        //artistTable.setVisible(true);
        //refreshArtist(new ActionEvent());
        initializeAll();
    }
    public void updateTables(ActionEvent e) {
        if(mode!=null) {
            switch (mode) {
                case ("Artist"):
                    refreshArtist(e);
                    break;
                case ("Booking"):
                    refreshBooking(e);
                    break;
                case ("CD"):
                    refreshCd(e);
                    break;
                case ("Consignment"):
                    refreshConsignment(e);
                    break;
                case ("Customer"):
                    refreshCustomer(e);
                    break;
                case ("Location"):
                    refreshLocation(e);
                    break;
                case ("Organizations"):
                    refreshOrganizations(e);
                    break;
                case ("Supply"):
                    refreshSupply(e);
                    break;
                case ("User"):
                    refreshUsers(e);
                    break;
            }
        }
    }
    public void addML(ActionEvent e){
        ModalWindow modal = new ModalWindow();
        modal.newWindow("Adding", "Music Label","");
    }
    public void addPr(ActionEvent e){
        ModalWindow modal = new ModalWindow();
        modal.newWindow("Adding", "Provider","");
    }
    private void wrongChoice(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText("Nothing is selected");
        alert.setContentText("Check correctness of your choice");
        alert.showAndWait();
    }
    public void setPaid(ActionEvent e){
        if(bookingTable.getSelectionModel().getSelectedItem()!=null){
            bookingTable.getSelectionModel().getSelectedItem().setFinished(true);
            ServiceList.bookingService.update(bookingTable.getSelectionModel().getSelectedItem());
        }
        else{
            wrongChoice();
        }
    }
    public void giveAdmin(ActionEvent e){
        if(userTable.getSelectionModel().getSelectedItem()!=null){
            userTable.getSelectionModel().getSelectedItem().setAdminMode(true);
            ServiceList.userService.update(userTable.getSelectionModel().getSelectedItem());
        }
        else{
            wrongChoice();
        }
    }
    public void takeAdmin(ActionEvent e){
        if(userTable.getSelectionModel().getSelectedItem()!=null){
            userTable.getSelectionModel().getSelectedItem().setAdminMode(false);
            ServiceList.userService.update(userTable.getSelectionModel().getSelectedItem());
        }
        else{
            wrongChoice();
        }
    }
    public void setNotPaid(ActionEvent e){
        if(bookingTable.getSelectionModel().getSelectedItem()!=null){
            bookingTable.getSelectionModel().getSelectedItem().setFinished(false);
            ServiceList.bookingService.update(bookingTable.getSelectionModel().getSelectedItem());
        }
        else{
            wrongChoice();
        }
    }
    public void editBookingPos(ActionEvent e){

        if(bookingPositionTable.getSelectionModel().getSelectedItem()!=null){
            int id=bookingPositionTable.getSelectionModel().getSelectedItem().getBookingId();
            int id2=bookingPositionTable.getSelectionModel().getSelectedItem().getConsignmentId();
            EditController.id2=id2;
            ModalWindow modal = new ModalWindow();
            modal.newWindow("Editing", "Booking Position",id);

        }
        else{
            return;
        }
    }
    public void editML(ActionEvent e){
        int id;
        if(labelTable.getSelectionModel().getSelectedItem()!=null){
            id=labelTable.getSelectionModel().getSelectedItem().getOrganizationId();
        }
        else{
            return;
        }
        ModalWindow modal = new ModalWindow();
        modal.newWindow("Editing", "Music Label",id);
    }
    public void editPr(ActionEvent e){
        int id;
        if(providerTable.getSelectionModel().getSelectedItem()!=null){
            id=providerTable.getSelectionModel().getSelectedItem().getOrganizationId();
        }
        else{
            return;
        }
        ModalWindow modal = new ModalWindow();
        modal.newWindow("Editing", "Provider",id);
    }
    public void editRecord(ActionEvent e){
        int id=0;
        if(mode!=null) {
            switch (mode) {
                case ("Artist"):
                    if (artistTable.getSelectionModel().getSelectedItem() != null) {
                        id = artistTable.getSelectionModel().getSelectedItem().getArtistId();
                    } else {
                        return;
                    }
                    break;
                case ("Booking"):
                    if (bookingTable.getSelectionModel().getSelectedItem() != null) {
                        id = bookingTable.getSelectionModel().getSelectedItem().getBookingId();
                    } else {
                        return;
                    }
                    break;
                case ("CD"):
                    if (cdTable.getSelectionModel().getSelectedItem() != null) {
                        id = cdTable.getSelectionModel().getSelectedItem().getCdId();
                    } else {
                        return;
                    }
                    break;
                case ("Consignment"):
                    if (consignmentTable.getSelectionModel().getSelectedItem() != null) {
                        id = consignmentTable.getSelectionModel().getSelectedItem().getConsignmentId();
                    } else {
                        return;
                    }
                    break;
                case ("Customer"):
                    if (customerTable.getSelectionModel().getSelectedItem() != null) {
                        id = customerTable.getSelectionModel().getSelectedItem().getCustomerId();
                    } else {
                        return;
                    }
                    break;
                case ("Location"):
                    if (locationTable.getSelectionModel().getSelectedItem() != null) {
                        id = locationTable.getSelectionModel().getSelectedItem().getLocationId();
                    } else {
                        return;
                    }
                    break;
                case ("Supply"):
                    if (supplyTable.getSelectionModel().getSelectedItem() != null) {
                        id = supplyTable.getSelectionModel().getSelectedItem().getSupplyId();
                    } else {
                        return;
                    }
                    break;
                case ("User"):
                    if (userTable.getSelectionModel().getSelectedItem() != null) {
                        id = userTable.getSelectionModel().getSelectedItem().getUserId();
                    } else {
                        return;
                    }
                    break;
            }

            try {
                ModalWindow modal = new ModalWindow();
                modal.newWindow("Editing", mode, id);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Nothing is selected to be edit");
                alert.setContentText("Check correctness of your choice");
                alert.showAndWait();
            }
        }
    }
    public void deletePosition(ActionEvent e){
        if(bookingPositionTable.getSelectionModel().getSelectedItem()!=null) {
            ServiceList.bookingService.deletePosition(bookingPositionTable.getSelectionModel().getSelectedItem());
        }
    }
    public void deleteRecord(ActionEvent e){
        try {
            if(mode!=null) {
                switch (mode) {
                    case ("Artist"):
                        ServiceList.artistService.delete(artistTable.getSelectionModel().getSelectedItem().getArtistId());
                        break;
                    case ("Booking"):
                        ServiceList.bookingService.delete(bookingTable.getSelectionModel().getSelectedItem().getBookingId());
                        break;
                    case ("CD"):
                        ServiceList.cdService.delete(cdTable.getSelectionModel().getSelectedItem().getCdId());
                        break;
                    case ("Consignment"):
                        ServiceList.consignmentService.delete(consignmentTable.getSelectionModel().getSelectedItem().getConsignmentId());
                        break;
                    case ("Customer"):
                        ServiceList.customerService.delete(customerTable.getSelectionModel().getSelectedItem().getCustomerId());
                        break;
                    case ("Location"):
                        ServiceList.locationService.delete(locationTable.getSelectionModel().getSelectedItem().getLocationId());
                        break;
                    case ("Supply"):
                        ServiceList.supplyService.delete(supplyTable.getSelectionModel().getSelectedItem().getSupplyId());
                        break;
                    case ("User"):
                        ServiceList.userService.delete(userTable.getSelectionModel().getSelectedItem().getUserId());
                        break;
                }
                updateTables(e);
            }
        }
        catch (Exception ex){Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing to be deleted");
            alert.setContentText("Check correctness of your choice");
            alert.showAndWait();}
    }
    public void addRecord(ActionEvent e){
        if(mode!=null) {
            try {
                ModalWindow modal = new ModalWindow();
                modal.newWindow("Adding", mode, "");
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Nothing is selected to be added to");
                alert.setContentText("Check correctness of your choice");
                alert.showAndWait();
            }
        }
    }

    public void changeUser(ActionEvent e){
        hideAll();
        try {
            ModalWindow modal=new ModalWindow();
            modal.newWindow("login","","");
            switchBox.getItems().clear();
            if(adminMode) {
                switchBox.setItems(FXCollections.observableArrayList("Artist", "Booking", "CD", "Consignment", "Customer", "Location",
                        "Organizations", "Supply", "User"));
            }
            else{
                switchBox.setItems(FXCollections.observableArrayList("Artist", "Booking", "CD", "Consignment", "Customer", "Location",
                        "Organizations", "Supply"));
            }

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void initializeAll(){
        initializeArtist(artistTable);
        initializeBooking();
        initializeCD();
        initializeConsignment(consignmentTable);
        initializeCustomer();
        initializeLocation(locationTable);
        initializeSupply();
        initializeUser();
        initializeOrganizations();
    }
    public static void initializeArtist(TableView<ArtistEntity> artistTable) {
        TableColumn<ArtistEntity, String> nameColumn = new TableColumn<>("Artist name");
        nameColumn.setMinWidth(344);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn<ArtistEntity, Integer> numColumn = new TableColumn<>("Number of albums");
        numColumn.setMinWidth(317);
        numColumn.setCellValueFactory(
                new PropertyValueFactory<>("numberOfAlbums"));
        artistTable.getColumns().clear();
        artistTable.getColumns().add(nameColumn);
        artistTable.getColumns().add(numColumn);
    }
    public void initializeBooking() {
        TableColumn<BookingEntity, Date> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(131);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        TableColumn<BookingEntity, Integer> priceColumn = new TableColumn<>("Total Price");
        priceColumn.setMinWidth(130);
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalPrice"));
        TableColumn<BookingEntity, Boolean> finishColumn = new TableColumn<>("Paid");
        finishColumn.setMinWidth(120);
        finishColumn.setCellValueFactory(
                new PropertyValueFactory<>("finished"));
        TableColumn<BookingEntity, String> cusfnameColumn = new TableColumn<>("Customer full name");
        cusfnameColumn.setMinWidth(283);
        /*cusfnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerByCustomerId.name"));*/
        cusfnameColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCustomerByCustomerId().getName()+"  "+p.getValue().getCustomerByCustomerId().getSurname()));
        bookingTable.getColumns().clear();
        bookingTable.getColumns().add(dateColumn);
        bookingTable.getColumns().add(priceColumn);
        bookingTable.getColumns().add(cusfnameColumn);
        bookingTable.getColumns().add(finishColumn);
        bookingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ServiceList.refresh();
                if(!ServiceList.bookingService.getBookingPosByBookingID(newSelection.getBookingId()).isEmpty()) {
                    ObservableList<BookingPositionEntity> list = FXCollections.observableList(ServiceList.bookingService.getBookingPosByBookingID(newSelection.getBookingId()));
                    bookingPositionTable.getItems().clear();
                    bookingPositionTable.setItems(list);
                }
                else{
                    bookingPositionTable.getItems().clear();
                }
            }
        });


        TableColumn<BookingPositionEntity, String> cdNameColumn = new TableColumn<>("CD name");
        cdNameColumn.setMinWidth(105);
        cdNameColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getConsignmentByConsignmentId().getCdByCdId().getAlbum()));


        TableColumn<BookingPositionEntity, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(80);
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

        TableColumn<BookingPositionEntity, Integer> singlePriceColumn = new TableColumn<>("CD price");
        singlePriceColumn.setMinWidth(100);
        singlePriceColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getConsignmentByConsignmentId().getPrice()));

        TableColumn<BookingPositionEntity, String> providerColumn = new TableColumn<>("Provider");
        providerColumn.setMinWidth(130);
        providerColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getConsignmentByConsignmentId().getProviderByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization()));

        TableColumn<BookingPositionEntity, String> musicLabelColumn = new TableColumn<>("Music Label");
        musicLabelColumn.setMinWidth(130);
        musicLabelColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getConsignmentByConsignmentId().getCdByCdId().getMusicLabelByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization()));

        bookingPositionTable.getColumns().clear();
        bookingPositionTable.getColumns().add(cdNameColumn);
        bookingPositionTable.getColumns().add(singlePriceColumn);
        bookingPositionTable.getColumns().add(quantityColumn);
        bookingPositionTable.getColumns().add(providerColumn);
        bookingPositionTable.getColumns().add(musicLabelColumn);
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
        firstChoice.getItems().addAll(FXCollections.observableArrayList("Blues","Jazz","Country","Chanson","Electronic music","Rock","Pop","Rap/Hip-hop"));
    }
    public static void initializeConsignment(TableView<ConsignmentEntity> consignmentTable){
        TableColumn<ConsignmentEntity, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(112);
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        TableColumn<ConsignmentEntity, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(129);
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        TableColumn<ConsignmentEntity, String> cdColumn = new TableColumn<>("CD");
        cdColumn.setMinWidth(133);
        cdColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCdByCdId().getAlbum()));

        TableColumn<ConsignmentEntity, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(133);
        artistColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCdByCdId().getArtistByArtistId().getName()));

        TableColumn<ConsignmentEntity, String> providColumn = new TableColumn<>("Provider");
        providColumn.setMinWidth(154);
        providColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getProviderByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization()));

        consignmentTable.getColumns().clear();
        consignmentTable.getColumns().add(cdColumn);
        consignmentTable.getColumns().add(artistColumn);
        consignmentTable.getColumns().add(providColumn);
        consignmentTable.getColumns().add(priceColumn);
        consignmentTable.getColumns().add(quantityColumn);

    }
    public void initializeCustomer(){
        TableColumn<CustomerEntity, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(130);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn<CustomerEntity, String> surnameColumn = new TableColumn<>("LName");
        surnameColumn.setMinWidth(130);
        surnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("surname"));
        TableColumn<CustomerEntity, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setMinWidth(130);
        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        TableColumn<CustomerEntity, String> mailColumn = new TableColumn<>("Email");
        mailColumn.setMinWidth(185);
        mailColumn.setCellValueFactory(
                new PropertyValueFactory<>("mail"));
        TableColumn<CustomerEntity, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setMinWidth(87);
        ageColumn.setMaxWidth(87);
        ageColumn.setCellValueFactory(
                new PropertyValueFactory<>("age"));
        customerTable.getColumns().clear();
        customerTable.getColumns().add(nameColumn);
        customerTable.getColumns().add(surnameColumn);
        customerTable.getColumns().add(phoneColumn);
        customerTable.getColumns().add(mailColumn);
        customerTable.getColumns().add(ageColumn);
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                countryLabel.setVisible(true);
                cityLabel.setVisible(true);
                streetLabel.setVisible(true);
                houseLabel.setVisible(true);
                postLabel.setVisible(true);
                countryLabel.setText("Country: "+newSelection.getLocationByLocationId().getCountry());
                cityLabel.setText("City: "+newSelection.getLocationByLocationId().getCity());
                streetLabel.setText("Street: "+newSelection.getLocationByLocationId().getStreet());
                houseLabel.setText("House: "+ newSelection.getLocationByLocationId().getHouse());
                postLabel.setText("Post code: "+String.valueOf(newSelection.getLocationByLocationId().getPostCode()));
            }
            else{
                countryLabel.setVisible(false);
                cityLabel.setVisible(false);
                streetLabel.setVisible(false);
                houseLabel.setVisible(false);
                postLabel.setVisible(false);
            }
        });
    }

    public static void initializeLocation(TableView<LocationEntity> locationTable){
        TableColumn<LocationEntity, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setMinWidth(124);
        countryColumn.setCellValueFactory(
                new PropertyValueFactory<>("country"));
        TableColumn<LocationEntity, String> cityColumn = new TableColumn<>("City");
        cityColumn.setMinWidth(117);
        cityColumn.setCellValueFactory(
                new PropertyValueFactory<>("city"));
        TableColumn<LocationEntity, String> streetColumn = new TableColumn<>("Street");
        streetColumn.setMinWidth(187);
        streetColumn.setCellValueFactory(
                new PropertyValueFactory<>("street"));
        TableColumn<LocationEntity, String> houseColumn = new TableColumn<>("House");
        houseColumn.setMinWidth(117);
        houseColumn.setCellValueFactory(
                new PropertyValueFactory<>("house"));
        TableColumn<LocationEntity, Integer> postColumn = new TableColumn<>("Post Code");
        postColumn.setMinWidth(118);
        postColumn.setCellValueFactory(
                new PropertyValueFactory<>("postCode"));
        locationTable.getColumns().clear();
        locationTable.getColumns().add(countryColumn);
        locationTable.getColumns().add(cityColumn);
        locationTable.getColumns().add(streetColumn);
        locationTable.getColumns().add(houseColumn);
        locationTable.getColumns().add(postColumn);
    }
    public void initializeSupply(){
        TableColumn<SupplyEntity, Integer> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        TableColumn<SupplyEntity, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalPrice"));
        TableColumn<SupplyEntity, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(95);
        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        TableColumn<SupplyEntity, String> cdColumn = new TableColumn<>("CD");
        cdColumn.setMinWidth(133);
        cdColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCdByCdId().getAlbum()));

        TableColumn<SupplyEntity, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(133);
        artistColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getCdByCdId().getArtistByArtistId().getName()));

        TableColumn<SupplyEntity, String> prColumn = new TableColumn<>("Provider");
        prColumn.setMinWidth(100);
        prColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getProviderByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization()));

        supplyTable.getColumns().clear();
        supplyTable.getColumns().add(dateColumn);
        supplyTable.getColumns().add(priceColumn);
        supplyTable.getColumns().add(quantityColumn);
        supplyTable.getColumns().add(cdColumn);
        supplyTable.getColumns().add(artistColumn);
        supplyTable.getColumns().add(prColumn);
        //deleteButton.setVisible(false);
    }
    public void initializeUser(){
        TableColumn<UserEntity, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setMinWidth(215);
        loginColumn.setCellValueFactory(
                new PropertyValueFactory<>("login"));
        TableColumn<UserEntity, String> passColumn = new TableColumn<>("Password");
        passColumn.setMinWidth(244);
        passColumn.setCellValueFactory(
                new PropertyValueFactory<>("password"));
        TableColumn<UserEntity, Boolean> adminColumn = new TableColumn<>("Admin rights");
        adminColumn.setMinWidth(203);
        adminColumn.setCellValueFactory(
                new PropertyValueFactory<>("adminMode"));
        userTable.getColumns().clear();
        userTable.getColumns().add(loginColumn);
        userTable.getColumns().add(passColumn);
        userTable.getColumns().add(adminColumn);
    }
    public void initializeOrganizations(){
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
    public void refreshArtist(ActionEvent e){
        ServiceList.refresh();
        ObservableList<ArtistEntity> list =FXCollections.observableList(ServiceList.artistService.getAll());
        artistTable.getItems().clear();
        artistTable.setItems(list);
    }
    public void refreshBooking(ActionEvent e){
        ServiceList.refresh();
        ObservableList<BookingEntity> list2 =FXCollections.observableList(ServiceList.bookingService.getAll());
        bookingTable.getItems().clear();
        bookingTable.setItems(list2);
    }
    public void refreshCd(ActionEvent e){
        ServiceList.refresh();
        ObservableList<CdEntity> list4 =FXCollections.observableList(ServiceList.cdService.getAll());
        cdTable.getItems().clear();
        cdTable.setItems(list4);
    }
    public void refreshConsignment(ActionEvent e){
        ServiceList.refresh();
        ObservableList<ConsignmentEntity> list5 =FXCollections.observableList(ServiceList.consignmentService.getAll());
        consignmentTable.getItems().clear();
        consignmentTable.setItems(list5);
    }
    public void refreshCustomer(ActionEvent e){
        ObservableList<CustomerEntity> list6 =FXCollections.observableList(ServiceList.customerService.getAll());
        customerTable.getItems().clear();
        customerTable.setItems(list6);
    }
    public void refreshLocation(ActionEvent e){
        ServiceList.refresh();
        ObservableList<LocationEntity> list7 =FXCollections.observableList(ServiceList.locationService.getAll());
        locationTable.getItems().clear();
        locationTable.setItems(list7);
    }
    public void refreshOrganizations(ActionEvent e){
        ServiceList.refresh();
        //ObservableList<OrganizationEntity> list8 =FXCollections.observableList(ServiceList.organizationsService.getAll());
        //organizationTable.getItems().clear();
        //organizationTable.setItems(list8);
        ObservableList<MusicLabelEntity> list9 =FXCollections.observableList(ServiceList.organizationsService.getAllMl());
        labelTable.getItems().clear();
        labelTable.setItems(list9);
        ObservableList<ProviderEntity> list10 =FXCollections.observableList(ServiceList.organizationsService.getAllPr());
        providerTable.getItems().clear();
        providerTable.setItems(list10);
    }
    public void refreshSupply(ActionEvent e){
        ServiceList.refresh();
        ObservableList<SupplyEntity> list11 =FXCollections.observableList(ServiceList.supplyService.getAll());
        supplyTable.getItems().clear();
        supplyTable.setItems(list11);
    }
    public void refreshUsers(ActionEvent e){
        ServiceList.refresh();
        ObservableList<UserEntity> list12=FXCollections.observableList(ServiceList.userService.getAll());
        userTable.getItems().clear();
        userTable.setItems(list12);
    }
    public void switchMode(ActionEvent e){
        hideAll();
        if(switchBox.getValue()!=null) {
            mode=switchBox.getValue();
            if(adminMode){
                deleteButton.setVisible(true);
            }
            addButton.setVisible(true);
            refreshButton.setVisible(true);
            updateButton.setVisible(true);
            firstChoice.getItems().clear();
            secondChoice.getItems().clear();
            switch (mode) {
                case ("Artist"):
                    artistTable.setVisible(true);
                    firstTextField.setVisible(true);
                    secondTextField.setVisible(true);
                    nameLabel.setVisible(true);
                    filterButton.setVisible(true);
                    albumsLabel.setVisible(true);
                    refreshArtist(e);
                    //initializeArtist();
                    break;
                case ("Booking"):
                    bookingTable.setVisible(true);
                    bookingPositionTable.setVisible(true);
                    if(adminMode) {
                        deletePositionButton.setVisible(true);
                        setNotPaidButton.setVisible(true);
                    }
                    setPaidButton.setVisible(true);
                    addPosButton.setVisible(true);
                    editPosButton.setVisible(true);
                    refreshBooking(e);
                    //initializeBooking();
                    break;
                case ("CD"):
                    cdTable.setVisible(true);
                    filterButton.setVisible(true);
                    artistLabel1.setVisible(true);
                    genreLabel.setVisible(true);
                    firstChoice.setVisible(true);
                    secondTextField.setVisible(true);
                    refreshCd(e);
                    //initializeCD();
                    break;
                case ("Consignment"):
                    consignmentTable.setVisible(true);
                    refreshConsignment(e);
                    //initializeConsignment();
                    break;
                case ("Customer"):
                    customerTable.setVisible(true);
                    lnameLabel.setVisible(true);
                    secondTextField.setVisible(true);
                    nameLabel.setVisible(true);
                    firstTextField.setVisible(true);
                    filterButton.setVisible(true);
                    refreshCustomer(e);
                    //initializeCustomer();
                    break;
                case ("Location"):
                    locationTable.setVisible(true);
                    refreshLocation(e);
                    //initializeLocation();
                    break;
                case ("Organizations"):
                    organizationsTabPane.setVisible(true);
                    labelTable.setVisible(true);
                    providerTable.setVisible(true);
                    deleteButton.setVisible(false);
                    addButton.setVisible(false);
                    refreshButton.setVisible(false);
                    updateButton.setVisible(false);
                    if(adminMode) {
                        deleteMLButton.setVisible(true);
                        deletePrButton.setVisible(true);
                    }
                    addPrButton.setVisible(true);
                    addMLButton.setVisible(true);
                    editPrButton.setVisible(true);
                    editMLButton.setVisible(true);
                    refreshOrganizations(e);
                    //initializeOrganizations();
                    break;
                case ("Supply"):
                    supplyTable.setVisible(true);
                    refreshSupply(e);
                    //initializeSupply();
                    break;
                case ("User"):
                    userTable.setVisible(true);
                    giveAdminButton.setVisible(true);
                    takeAdminButton.setVisible(true);
                    refreshUsers(e);
                    break;
                default:
                    hideAll();
            }
        }
    }
    public void close(ActionEvent e){
        exit(0);
    }
    public void showStatistics(ActionEvent e){
        mode=null;
        hideAll();
        statisticsLabel.setVisible(true);
        weekLabel.setVisible(true);
        monthLabel.setVisible(true);
        amountWeekLabel.setVisible(true);
        amountMonthLabel.setVisible(true);
        saleWeekSumLabel.setVisible(true);
        saleMonthSumLabel.setVisible(true);
        disksWeekLabel.setVisible(true);
        disksMonthLabel.setVisible(true);
        supplyWeekLabel.setVisible(true);
        supplyMonthLabel.setVisible(true);
        Date date=new Date(System.currentTimeMillis()-604800000L);
        amountWeekLabel.setText("Amount of sold disks: "+ServiceList.bookingService.getAmount(date));
        saleWeekSumLabel.setText("Total sum of the sale: "+ServiceList.bookingService.getSales(date));
        disksWeekLabel.setText("Disks supplied: "+ServiceList.supplyService.getAmount(date));
        supplyWeekLabel.setText("For price: "+ServiceList.supplyService.getTotal(date));
        date.setTime(System.currentTimeMillis()-2629746000L);
        amountMonthLabel.setText("Amount of sold disks: "+ServiceList.bookingService.getAmount(date));
        saleMonthSumLabel.setText("Total sum of the sale: "+ServiceList.bookingService.getSales(date));
        disksMonthLabel.setText("Disks supplied: "+ServiceList.supplyService.getAmount(date));
        supplyMonthLabel.setText("For price: "+ServiceList.supplyService.getTotal(date));
    }
    public void hideAll(){
        artistTable.setVisible(false);
        bookingTable.setVisible(false);
        bookingPositionTable.setVisible(false);
        cdTable.setVisible(false);
        consignmentTable.setVisible(false);
        customerTable.setVisible(false);
        locationTable.setVisible(false);
        labelTable.setVisible(false);
        organizationsTabPane.setVisible(false);
        providerTable.setVisible(false);
        supplyTable.setVisible(false);
        userTable.setVisible(false);
        deletePositionButton.setVisible(false);
        deleteMLButton.setVisible(false);
        deletePrButton.setVisible(false);
        addPosButton.setVisible(false);
        editPosButton.setVisible(false);
        addPrButton.setVisible(false);
        addMLButton.setVisible(false);
        editPrButton.setVisible(false);
        editMLButton.setVisible(false);
        deleteButton.setVisible(false);
        setPaidButton.setVisible(false);
        setNotPaidButton.setVisible(false);
        countryLabel.setVisible(false);
        cityLabel.setVisible(false);
        streetLabel.setVisible(false);
        houseLabel.setVisible(false);
        postLabel.setVisible(false);
        giveAdminButton.setVisible(false);
        takeAdminButton.setVisible(false);
        filterButton.setVisible(false);
        artistLabel1.setVisible(false);
        genreLabel.setVisible(false);
        firstChoice.setVisible(false);
        secondChoice.setVisible(false);
        firstTextField.setVisible(false);
        lnameLabel.setVisible(false);
        secondTextField.setVisible(false);
        nameLabel.setVisible(false);
        statisticsLabel.setVisible(false);
        weekLabel.setVisible(false);
        monthLabel.setVisible(false);
        amountWeekLabel.setVisible(false);
        amountMonthLabel.setVisible(false);
        saleWeekSumLabel.setVisible(false);
        saleMonthSumLabel.setVisible(false);
        disksWeekLabel.setVisible(false);
        disksMonthLabel.setVisible(false);
        supplyWeekLabel.setVisible(false);
        supplyMonthLabel.setVisible(false);
        aNameLabel.setVisible(false);
        albumsLabel.setVisible(false);
    }
    public void deleteML(ActionEvent e){
        if(labelTable.getSelectionModel().getSelectedItem()!=null) {
            ServiceList.organizationsService.deleteLabel(labelTable.getSelectionModel().getSelectedItem().getOrganizationId());
        }
        updateTables(e);
    }
    public void filterCD(ActionEvent e){
        ServiceList.refresh();
        if(switchBox.getValue()!=null) {
            mode=switchBox.getValue();
            switch (mode) {
                case ("Artist"):
                    String aName="";
                    String albums="";
                    if(firstTextField.getText()!=null){
                        aName=firstTextField.getText();
                    }
                    if(secondTextField.getText()!=null){
                        albums=secondTextField.getText();
                    }
                    ObservableList<ArtistEntity> list3 =FXCollections.observableList(ServiceList.artistService.getAllFiltered(aName,albums));
                    artistTable.getItems().clear();
                    artistTable.setItems(list3);
                    break;
                case ("CD"):
                    String name1="";
                    String genre="";
                    if(secondTextField.getText()!=null){
                        name1=secondTextField.getText();
                    }
                    if(firstChoice.getValue()!=null){
                        genre=firstChoice.getValue();
                    }
                    ObservableList<CdEntity> list4 =FXCollections.observableList(ServiceList.cdService.getAllFiltered(genre,name1));
                    cdTable.getItems().clear();
                    cdTable.setItems(list4);
                    break;
                case ("Customer"):
                    String cname1="";
                    String cname2="";
                    if(firstTextField.getText()!=null){
                        cname1=firstTextField.getText();
                    }
                    if(secondTextField.getText()!=null){
                        cname2=secondTextField.getText();
                    }
                    ObservableList<CustomerEntity> list2 =FXCollections.observableList(ServiceList.customerService.getAllFiltered(cname1,cname2));
                    customerTable.getItems().clear();
                    customerTable.setItems(list2);
                    break;
            }
        }


    }
    public void deletePr(ActionEvent e){
        if(providerTable.getSelectionModel().getSelectedItem()!=null) {
            ServiceList.organizationsService.deleteProvider(providerTable.getSelectionModel().getSelectedItem().getOrganizationId());
        }
        updateTables(e);
    }
    public void refreshML(ActionEvent e){
        ObservableList<MusicLabelEntity> list9 =FXCollections.observableList(ServiceList.organizationsService.getAllMl());
        labelTable.getItems().clear();
        labelTable.setItems(list9);
    }
    public void refreshPr(ActionEvent e){
        ObservableList<ProviderEntity> list10 =FXCollections.observableList(ServiceList.organizationsService.getAllPr());
        providerTable.getItems().clear();
        providerTable.setItems(list10);
    }
    public void author(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Software was developed by student of 3 course, group CS-34g Artyom Kharin");
        alert.setContentText("27.12.2016");
        alert.showAndWait();
    }
    public void addPos(ActionEvent e) {
        try {
            ModalWindow modal = new ModalWindow();
            modal.newWindow("Adding", "Booking Position", String.valueOf(bookingTable.getSelectionModel().getSelectedItem().getBookingId()));
        }
        catch (Exception ex){
            wrongChoice();
        }
    }
    public void howTo(ActionEvent e){
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "start", "help\\index.htm");
            Process process = builder.start();
        }
        catch(IOException eeee){}
    }

}
