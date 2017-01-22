package net.gui.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.gui.dao.MusicLabelDAO;
import net.gui.models.*;
import net.gui.services.ServiceList;
import net.gui.utils.HibernateSessionManager;
import net.gui.utils.Validator;

import javax.annotation.Resources;
import javax.persistence.criteria.CriteriaBuilder;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by EvSpirit on 22.12.2016.
 */
public class EditController implements Initializable {
    public static String mode;
    @FXML
    private Label modeLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button commitButton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
    @FXML
    private TextField field4;
    @FXML
    private TextField field5;
    @FXML
    private TextField field6;
    @FXML
    private TextField field7;
    @FXML
    private ChoiceBox<String> genre;
    public static int id;
    public static int id2;
    public EditController(){}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideAll();
        if(mode!=null) {
            modeLabel.setText(mode);
            switch (mode) {
                case ("Artist"):artistFill();
                    break;
                case ("Booking"):bookingFill();
                    break;
                case("Booking Position"):bookingPositionFill();
                    break;
                case ("CD"):cdFill();
                    break;
                case ("Consignment"):consignmentFill();
                    break;
                case ("Customer"):customerFill();
                    break;
                case ("Location"):locationFill();
                    break;
                case ("Organizations"):organizationFill();
                    break;
                case ("Music Label"):musicLabelFill();
                    break;
                case ("Provider"):providerFill();
                    break;
                case ("Supply"):supplyFill();
                    break;
                case ("User"):userFill();
                    break;
            }
        }
    }
    public void hideAll(){
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        label5.setVisible(false);
        label6.setVisible(false);
        label7.setVisible(false);
        field1.setVisible(false);
        field2.setVisible(false);
        field3.setVisible(false);
        field4.setVisible(false);
        field5.setVisible(false);
        field6.setVisible(false);
        field7.setVisible(false);
        genre.setVisible(false);
    }
    public void artistFill(){
        label1.setText("Name");
        label2.setText("Number of albums");
        label1.setVisible(true);
        label2.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        ArtistEntity artistEntity=ServiceList.artistService.selectById(id);
        field1.setText(artistEntity.getName());
        field2.setText(String.valueOf(artistEntity.getNumberOfAlbums()));
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field2.getText().length() > 3) {
                    String s = field2.getText().substring(0, 3);
                    field2.setText(s);
                }
                Validator.numberConstraint(field2);
            }
        });
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field1.getText().length() > 20) {
                    String s = field1.getText().substring(0, 20);
                    field1.setText(s);
                }
                if(!Validator.nameConstraint(field1.getText())){
                    String s = field1.getText().substring(0, field1.getText().length()-1);
                    field1.setText(s);
                }

            }
        });

    }
    public void bookingFill(){
        label1.setText("Customer_ID");
        label1.setVisible(true);
        field1.setVisible(true);
        BookingEntity bookingEntity = ServiceList.bookingService.selectById(id);
        field1.setText(String.valueOf(bookingEntity.getCustomerId()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field1.getText().length() > 11) {
                    String s = field1.getText().substring(0, 11);
                    field1.setText(s);
                }
                Validator.numberConstraint(field1);
            }
        });
    }
    public void bookingPositionFill() {
        label1.setText("Consignment_ID");
        label2.setText("Quantity");
        label1.setVisible(true);
        label2.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        BookingPositionEntity bookingPositionEntity = ServiceList.bookingService.getBookingPosByComposite(id, id2);
        field1.setText(String.valueOf(bookingPositionEntity.getConsignmentId()));
        field2.setText(String.valueOf(bookingPositionEntity.getQuantity()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field1);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field2, 3);
                Validator.numberConstraint(field2);
            }
        });
    }
    public void cdFill(){
        label1.setText("Album");
        label2.setText("Genre");
        label3.setText("Artist ID");
        label4.setText("Music Label ID");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        field1.setVisible(true);
        //field2.setVisible(true);
        genre.getItems().addAll(FXCollections.observableArrayList("Blues","Jazz","Country","Chanson","Electronic music","Rock","Pop","Rap/Hip-hop"));
        genre.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        CdEntity cdEntity=ServiceList.cdService.selectById(id);
        genre.setValue(cdEntity.getGenre());
        field1.setText(cdEntity.getAlbum());
//        field2.setText(cdEntity.getGenre());
        field3.setText(String.valueOf(cdEntity.getArtistId()));
        field4.setText(String.valueOf(cdEntity.getOrganizationId()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field1,20);
            }
        });
        /*field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field2,15);
            }
        });*/
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field3);
            }
        });
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field4);
            }
        });
    }
    public void consignmentFill(){
        label1.setText("Quantity");
        label2.setText("CD ID");
        label3.setText("Provider ID");
        label4.setText("Price");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        ConsignmentEntity consignmentEntity=ServiceList.consignmentService.selectById(id);
        field1.setText(String.valueOf(consignmentEntity.getQuantity()));
        field2.setText(String.valueOf(consignmentEntity.getCdId()));
        field3.setText(String.valueOf(consignmentEntity.getOrganizationId()));
        field4.setText(String.valueOf(consignmentEntity.getPrice()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field1);
                Validator.lengthConstraint(field1,3);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field2);
            }
        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field3);
            }
        });
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field4);
                Validator.lengthConstraint(field4,5);
            }
        });
    }
    public void customerFill(){
        label1.setText("Name");
        label2.setText("LName");
        label3.setText("Phone");
        label4.setText("E-mail");
        label5.setText("Age");
        label6.setText("Location Id");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        field5.setVisible(true);
        field6.setVisible(true);
        CustomerEntity customerEntity=ServiceList.customerService.selectById(id);
        field1.setText(customerEntity.getName());
        field2.setText(customerEntity.getSurname());
        field3.setText(customerEntity.getPhone());
        field4.setText(customerEntity.getMail());
        field5.setText(String.valueOf(customerEntity.getAge()));
        field6.setText(String.valueOf(customerEntity.getLocationId()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(field1.getText().length()>0) {
                    if (field1.getText().length() > 20) {
                        String s = field1.getText().substring(0, 20);
                        field1.setText(s);
                    }
                    if (!Validator.nameConstraint(field1.getText())) {
                        String s = field1.getText().substring(0, field1.getText().length() - 1);
                        field1.setText(s);
                    }
                }
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field1.getText().length() > 0) {
                    if (field2.getText().length() > 20) {
                        String s = field2.getText().substring(0, 20);
                        field2.setText(s);
                    }

                    if (!Validator.nameConstraint(field2.getText())) {
                        String s = field2.getText().substring(0, field2.getText().length() - 1);
                        field2.setText(s);
                    }
                }
            }

        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field3,13);
                Validator.phoneConstraint(field3);
            }
        });
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field4,32);
                if(field4.getText().length()>0&&Validator.validationMailString.indexOf(field4.getText().charAt(field4.getText().length()-1))==-1){
                    String s = field4.getText().substring(0,field4.getText().length() - 1);
                    field4.setText(s);
                }
            }
        });
        field5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field5);
                Validator.lengthConstraint(field5,3);
            }
        });
        field6.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field6);
            }
        });
    }
    public void locationFill(){
        label1.setText("Country");
        label2.setText("City");
        label3.setText("Street");
        label4.setText("House");
        label5.setText("Post code");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        field5.setVisible(true);
        LocationEntity locationEntity=ServiceList.locationService.selectById(id);
        field1.setText(locationEntity.getCountry());
        field2.setText(locationEntity.getCity());
        field3.setText(locationEntity.getStreet());
        field4.setText(locationEntity.getHouse());
        field5.setText(String.valueOf(locationEntity.getPostCode()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field1,20);
                Validator.alphabetConstraint(field1);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field2,20);
                Validator.alphabetConstraint(field2);
            }
        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field3,20);
                Validator.alphabetConstraint(field3);
            }
        });
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field4,10);
                if(field4.getText().length()>0) {
                    if (!Validator.nameConstraint(field4.getText())) {
                        String s = field4.getText().substring(0, field4.getText().length() - 1);
                        field4.setText(s);
                    }
                }
            }
        });
        field5.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field5);
                Validator.lengthConstraint(field5,6);
            }
        });
    }
    public void musicLabelFill(){
        label1.setText("Organization_ID");
        label2.setText("Studio amount");
        label1.setVisible(true);
        label2.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        MusicLabelEntity musicLabelEntity=ServiceList.organizationsService.selectByIdML(id);
        field1.setText(String.valueOf(musicLabelEntity.getOrganizationId()));
        field2.setText(String.valueOf(musicLabelEntity.getStudioAmount()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field1);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field2);
                Validator.lengthConstraint(field2,3);
            }
        });
    }
    public void organizationFill(){
        label1.setText("Name");
        label2.setText("Phone");
        label3.setText("E-mail");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        OrganizationEntity organizationEntity=ServiceList.organizationsService.selectById(id);
        field1.setText(organizationEntity.getNameOfOrganization());
        field2.setText(organizationEntity.getPhone());
        field3.setText(organizationEntity.getMail());
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field1.getText().length() > 0) {
                    if (field1.getText().length() > 30) {
                        String s = field1.getText().substring(0, 30);
                        field1.setText(s);
                    }

                    if (!Validator.nameConstraint(field1.getText())) {
                        String s = field1.getText().substring(0, field1.getText().length() - 1);
                        field1.setText(s);
                    }
                }
            }

        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field2,13);
                Validator.phoneConstraint(field2);
            }
        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field3,32);
                if(field3.getText().length()>0&&Validator.validationMailString.indexOf(field3.getText().charAt(field3.getText().length()-1))==-1){
                    String s = field3.getText().substring(0,field3.getText().length() - 1);
                    field3.setText(s);
                }
            }
        });
    }
    public void providerFill(){
        label1.setText("Organization_ID");
        label2.setText("Location ID");
        label3.setText("ITN");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        ProviderEntity providerEntity=ServiceList.organizationsService.selectByIdPr(id);
        field1.setText(String.valueOf(providerEntity.getOrganizationId()));
        field2.setText(String.valueOf(providerEntity.getLocationId()));
        field3.setText(providerEntity.getItn());
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field1);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field2);
            }
        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field3,12);
                Validator.numberConstraint(field3);
            }
        });
    }
    public void supplyFill(){
        label1.setText("Total price");
        label2.setText("Quantity");
        label3.setText("Provider ID");
        label4.setText("CD ID");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        SupplyEntity supplyEntity=ServiceList.supplyService.selectById(id);
        field1.setText(String.valueOf(supplyEntity.getTotalPrice()));
        field2.setText(String.valueOf(supplyEntity.getQuantity()));
        field3.setText(String.valueOf(supplyEntity.getOrganizationId()));
        field4.setText(String.valueOf(supplyEntity.getCdId()));
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field1,4);
                Validator.numberConstraint(field1);
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field2,3);
                Validator.numberConstraint(field2);
            }
        });
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field3);
            }
        });
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.idConstraint(field4);
            }
        });
    }
    public void userFill(){
        label1.setText("Login");
        label2.setText("Password");
        label1.setVisible(true);
        label2.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        UserEntity userEntity=ServiceList.userService.selectById(id);
        field1.setText(userEntity.getLogin());
        field2.setText(userEntity.getPassword());
        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field1.getText().length() > 16) {
                    String s = field1.getText().substring(0, 16);
                    field1.setText(s);
                }

                if(field1.getText().length()>0&&Validator.validationString.indexOf(field1.getText().charAt(field1.getText().length()-1))==-1){
                    String s = field1.getText().substring(0,field1.getText().length() - 1);
                    field1.setText(s);
                }
            }
        });
        field2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (field2.getText().length() > 16) {
                    String s = field2.getText().substring(0, 16);
                    field2.setText(s);
                }
            }
        });
    }
    public void commitAdd(ActionEvent e){
        try {
            if (mode != null) {
                switch (mode) {
                    case ("Artist"):
                        ArtistEntity entity = ServiceList.artistService.selectById(id);
                        if (field1.getText().length() < 2)
                            throw new Exception("Name should contain at least 2 symbols");
                        if (field2.getText().length() == 0) throw new Exception("Number of albums should be non-empty");
                        entity.setName(field1.getText());
                        entity.setNumberOfAlbums(Integer.parseInt(field2.getText()));
                        ServiceList.artistService.update(entity);
                        break;
                    case ("Booking"):
                        BookingEntity bookingEntity = ServiceList.bookingService.selectById(id);
                        if(field1.getText().length()==0) throw new Exception("Customer ID should be non-empty");
                        bookingEntity.setCustomerId(Integer.parseInt(field1.getText()));
                        ServiceList.bookingService.update(bookingEntity);
                        break;
                    case ("Booking Position"):
                        BookingPositionEntity bookingPositionEntity = ServiceList.bookingService.getBookingPosByComposite(id, id2);
                        if(field2.getText().length()==0) throw new Exception("Quantity can't be null");
                        if(field1.getText().length()==0) throw new Exception("Consignment  ID should be non-empty");
                        bookingPositionEntity.setConsignmentId(Integer.parseInt(field1.getText()));
                        bookingPositionEntity.setQuantity(Integer.parseInt(field2.getText()));
                        ServiceList.bookingService.updatePosition(bookingPositionEntity);
                        break;
                    case ("CD"):
                        CdEntity cdEntity = ServiceList.cdService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Name of album should contain at least 2 symbols");
                        if(genre.getSelectionModel().getSelectedItem()==null) throw new Exception("Choose genre");
                        if(field3.getText().length()==0) throw new Exception("Artist Id should contain al least 1 symbol");
                        if(field4.getText().length()==0) throw new Exception("Music Label ID should contain at least 1 symbol");
                        cdEntity.setAlbum(field1.getText());
                        cdEntity.setGenre(genre.getValue());
                        cdEntity.setArtistId(Integer.parseInt(field3.getText()));
                        cdEntity.setOrganizationId(Integer.parseInt(field4.getText()));
                        ServiceList.cdService.update(cdEntity);
                        break;
                    case ("Consignment"):
                        ConsignmentEntity consignmentEntity = ServiceList.consignmentService.selectById(id);
                        if(field1.getText().length()<1) throw new Exception("Quantity can't be null");
                        if(field2.getText().length()==0) throw new Exception("CD Id should contain al least 1 symbol");
                        if(field3.getText().length()==0) throw new Exception("Provider ID should contain at least 1 symbol");
                        if(field4.getText().length()<1) throw new Exception("Price can't be null");
                        consignmentEntity.setQuantity(Integer.parseInt(field1.getText()));
                        consignmentEntity.setCdId(Integer.parseInt(field2.getText()));
                        consignmentEntity.setOrganizationId(Integer.parseInt(field3.getText()));
                        consignmentEntity.setPrice(Integer.parseInt(field4.getText()));
                        ServiceList.consignmentService.update(consignmentEntity);
                        break;
                    case ("Customer"):
                        CustomerEntity customerEntity = ServiceList.customerService.selectById(id);
                        if(field1.getText().length()<1) throw new Exception("Name can't be null");
                        if(field2.getText().length()<1) throw new Exception("LName can't be null");
                        if(field3.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field4.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        if(field5.getText().length()==0) throw new Exception("Age can't be null");
                        if(field6.getText().length()==0) throw new Exception("Location ID can't be null");
                        customerEntity.setName(field1.getText());
                        customerEntity.setSurname(field2.getText());
                        customerEntity.setPhone(field3.getText());
                        customerEntity.setMail(field4.getText());
                        customerEntity.setAge(Integer.parseInt(field5.getText()));
                        customerEntity.setLocationId(Integer.parseInt(field6.getText()));
                        ServiceList.customerService.update(customerEntity);
                        break;
                    case ("Location"):
                        LocationEntity locationEntity = ServiceList.locationService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Country should contain at least 2 symbols");
                        if(field2.getText().length()<2) throw new Exception("City should contain at least 2 symbols");
                        if(field3.getText().length()<2) throw new Exception("Street should contain at least 2 symbols");
                        if(field4.getText().length()<1) throw new Exception("House number should contain at least 1 symbol");
                        if(field5.getText().length()<3) throw new Exception("Post code should contain at least 3 symbols");
                        locationEntity.setCountry(field1.getText());
                        locationEntity.setCity(field2.getText());
                        locationEntity.setStreet(field3.getText());
                        locationEntity.setHouse(field4.getText());
                        locationEntity.setPostCode(Integer.parseInt(field5.getText()));
                        ServiceList.locationService.update(locationEntity);
                        break;
                    case ("Organizations"):
                        OrganizationEntity organizationEntity = ServiceList.organizationsService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Name of organization should contain at least 2 symbols");
                        if(field2.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field3.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        organizationEntity.setNameOfOrganization(field1.getText());
                        organizationEntity.setPhone(field2.getText());
                        organizationEntity.setMail(field3.getText());
                        ServiceList.organizationsService.update(organizationEntity);
                        break;
                    case ("Music Label"):
                        MusicLabelEntity musicLabelEntity = ServiceList.organizationsService.selectByIdML(id);
                        if(field1.getText().length()<1) throw new Exception("Organization ID can't be null");
                        if(field2.getText().length()<1) throw new Exception("Record studio amount can't be null");
                        musicLabelEntity.setOrganizationId(Integer.parseInt(field1.getText()));
                        musicLabelEntity.setStudioAmount(Integer.parseInt(field2.getText()));
                        ServiceList.organizationsService.updateML(musicLabelEntity);
                        break;
                    case ("Provider"):
                        ProviderEntity providerEntity = ServiceList.organizationsService.selectByIdPr(id);
                        if(field1.getText().length()<1) throw new Exception("Organization ID can't be null");
                        if(field2.getText().length()<1) throw new Exception("Location ID can't be null");
                        if(field3.getText().length()<10) throw new Exception("ITN should contain from 10 to 12 numbers");
                        providerEntity.setOrganizationId(Integer.parseInt(field1.getText()));
                        providerEntity.setLocationId(Integer.parseInt(field2.getText()));
                        providerEntity.setItn(field3.getText());
                        ServiceList.organizationsService.updatePr(providerEntity);
                        break;
                    case ("Supply"):
                        SupplyEntity supplyEntity = ServiceList.supplyService.selectById(id);
                        if(field1.getText().length()<1)throw new Exception("Total Price can't be null");
                        if(field2.getText().length()<1) throw new Exception("Quantity can't be null");
                        if(field3.getText().length()<1) throw new Exception("Provider ID can't be null");
                        if(field4.getText().length()<1) throw new Exception("CD ID can't be null");
                        supplyEntity.setTotalPrice(Double.parseDouble(field1.getText()));
                        supplyEntity.setQuantity(Integer.parseInt(field2.getText()));
                        supplyEntity.setOrganizationId(Integer.parseInt(field3.getText()));
                        supplyEntity.setCdId(Integer.parseInt(field4.getText()));
                        ServiceList.supplyService.update(supplyEntity);
                        break;
                    case ("User"):
                        UserEntity userEntity = ServiceList.userService.selectById(id);
                        if(field1.getText().length()<6)throw new Exception("Login should contain at least 6 symbols");
                        if(field2.getText().length()<6) throw new Exception("Password should contain at least 6 symbols");
                        userEntity.setLogin(field1.getText());
                        userEntity.setPassword(field2.getText());
                        ServiceList.userService.update(userEntity);
                        break;
                }
            }
            cancel(e);
        }
        catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong data, try again");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    public void cancel(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
