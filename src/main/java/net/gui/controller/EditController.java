package net.gui.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.gui.ChoiceWindow;
import net.gui.ModalWindow;
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
    public static int consID,cdID,locID,labelID,prID,artID;
    @FXML
    private Label consignmentLabel;
    @FXML
    private Button consignmentChooseButton;
    @FXML
    private Label locationLabel;
    @FXML
    private Button locationChooseButton;
    @FXML
    private Label artistLabel;
    @FXML
    private Button artistChooseButton;
    @FXML
    private Label musicLabel;
    @FXML
    private Button providerChooseButton;
    @FXML
    private Button labelChooseButton;
    @FXML
    private Label providerLabel;
    @FXML
    private Button cdChooseButton;
    @FXML
    private Label cdLabel;
    public static int id,id2;
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
        consignmentLabel.setVisible(false);
        consignmentChooseButton.setVisible(false);
        artistLabel.setVisible(false);
        artistChooseButton.setVisible(false);
        musicLabel.setVisible(false);
        labelChooseButton.setVisible(false);
        locationLabel.setVisible(false);
        locationChooseButton.setVisible(false);
        providerLabel.setVisible(false);
        providerChooseButton.setVisible(false);
        cdLabel.setVisible(false);
        providerLabel.setVisible(false);
        cdChooseButton.setVisible(false);

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
        label1.setText("Customer name");
        label2.setText("Customer surname");
        label3.setText("Phone");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
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
                if (field2.getText().length() > 0) {
                    if (field2.getText().length() > 20) {
                        String s = field2.getText().substring(0, 20);
                        field2.setText(s);
                    }

                    if (!Validator.nameConstraint(field2.getText())) {
                        if(field2.getText().length()>0) {
                            String s = field2.getText().substring(0, field2.getText().length() - 1);
                            field2.setText(s);
                        }
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
        BookingEntity bookingEntity = ServiceList.bookingService.selectById(id);
        field1.setText(bookingEntity.getCustomerByCustomerId().getName());
        field2.setText(bookingEntity.getCustomerByCustomerId().getSurname());
        field3.setText(bookingEntity.getCustomerByCustomerId().getPhone());
    }
    public void bookingPositionFill() {
        label1.setText("Consignment");
        label3.setText("Quantity");
        consignmentLabel.setVisible(true);
        consignmentChooseButton.setVisible(true);
        label1.setVisible(true);
        label3.setVisible(true);
        field3.setVisible(true);
        BookingPositionEntity bookingPositionEntity = ServiceList.bookingService.getBookingPosByComposite(id, id2);
        consID=bookingPositionEntity.getConsignmentId();
        consignmentLabel.setText(ServiceList.consignmentService.selectById(consID).getCdByCdId().getAlbum()+";  "+ServiceList.consignmentService.selectById(consID).getProviderByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization());
        field3.setText(String.valueOf(bookingPositionEntity.getQuantity()));
        field3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field3,3);
                Validator.numberConstraint(field3);
            }
        });
    }
    public void cdFill(){
        label1.setText("Album");
        label2.setText("Genre");
        label3.setText("Artist");
        label5.setText("Music Label");
        artistLabel.setVisible(true);
        artistChooseButton.setVisible(true);
        musicLabel.setVisible(true);
        labelChooseButton.setVisible(true);
        /*label3.setText("Artist ID");
        label4.setText("Music Label ID");*/
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label5.setVisible(true);
        /*label3.setVisible(true);
        label4.setVisible(true);*/
        field1.setVisible(true);
        //field2.setVisible(true);
        genre.getItems().addAll(FXCollections.observableArrayList("Blues","Jazz","Country","Chanson","Electronic","Rock","Pop","Rap/Hip-hop"));
        genre.setVisible(true);
        /*field3.setVisible(true);
        field4.setVisible(true);*/

        field1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field1,20);
            }
        });        CdEntity cdEntity=ServiceList.cdService.selectById(id);
        genre.setValue(cdEntity.getGenre());
        field1.setText(cdEntity.getAlbum());
//        field2.setText(cdEntity.getGenre());
        artID=cdEntity.getArtistId();
        ArtistEntity art=ServiceList.artistService.selectById(artID);
        artistLabel.setText(art.getName()+", "+art.getNumberOfAlbums()+" albums");

        labelID=cdEntity.getOrganizationId();
        MusicLabelEntity mus=ServiceList.organizationsService.selectByIdML(labelID);
        musicLabel.setText(mus.getOrganizationByOrganizationId().getNameOfOrganization()+", "+mus.getOrganizationByOrganizationId().getMail());

    }
    public void consignmentFill(){
        label1.setText("Quantity");
        label3.setText("CD");
        label5.setText("Provider");
        label2.setText("Price");
        providerLabel.setVisible(true);
        providerChooseButton.setVisible(true);
        cdLabel.setVisible(true);
        cdChooseButton.setVisible(true);
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label5.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        /*field3.setVisible(true);
        field4.setVisible(true);*/
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
                Validator.numberConstraint(field2);
                Validator.lengthConstraint(field2,5);
            }
        });        ConsignmentEntity consignmentEntity=ServiceList.consignmentService.selectById(id);
        field1.setText(String.valueOf(consignmentEntity.getQuantity()));
        field2.setText(String.valueOf(consignmentEntity.getPrice()));
        cdID=consignmentEntity.getCdId();
        prID=consignmentEntity.getOrganizationId();
        CdEntity cd=ServiceList.cdService.selectById(cdID);
        cdLabel.setText(cd.getAlbum()+", "+cd.getArtistByArtistId().getName());
        ProviderEntity prov=ServiceList.organizationsService.selectByIdPr(prID);
        providerLabel.setText(prov.getOrganizationByOrganizationId().getNameOfOrganization()+", "+prov.getOrganizationByOrganizationId().getMail());
    }

    public void customerFill(){
        label1.setText("Name");
        label2.setText("LName");
        label3.setText("Phone");
        label4.setText("E-mail");
        label5.setText("Age");
        label6.setText("Location");
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
        locationLabel.setVisible(true);
        locationChooseButton.setVisible(true);
        locationLabel.setLayoutY(330);
        locationChooseButton.setLayoutY(363);
        //field6.setVisible(true);
        CustomerEntity customerEntity=ServiceList.customerService.selectById(id);
        field1.setText(customerEntity.getName());
        field2.setText(customerEntity.getSurname());
        field3.setText(customerEntity.getPhone());
        field4.setText(customerEntity.getMail());
        field5.setText(String.valueOf(customerEntity.getAge()));
        //field6.setText(String.valueOf(customerEntity.getLocationId()));
        locID=customerEntity.getLocationId();
        LocationEntity loc=ServiceList.locationService.selectById(locID);
        locationLabel.setText(loc.getCountry()+", "+loc.getCity()+", "+loc.getStreet()+", "+loc.getHouse());

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
        label1.setText("Name");
        label2.setText("Phone");
        label3.setText("E-mail");
        label4.setText("Studio amount");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        label4.setVisible(true);
        field4.setVisible(true);
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
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.numberConstraint(field4);
                Validator.lengthConstraint(field4,3);
            }
        });
        MusicLabelEntity musicLabelEntity=ServiceList.organizationsService.selectByIdML(id);
        field1.setText(musicLabelEntity.getOrganizationByOrganizationId().getNameOfOrganization());
        field2.setText(musicLabelEntity.getOrganizationByOrganizationId().getPhone());
        field3.setText(musicLabelEntity.getOrganizationByOrganizationId().getMail());
        field4.setText(String.valueOf(musicLabelEntity.getStudioAmount()));
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
        label1.setText("Name");
        label2.setText("Phone");
        label3.setText("E-mail");
        label4.setText("ITN");
        label5.setText("Location");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        field3.setVisible(true);
        field4.setVisible(true);
        locationLabel.setVisible(true);
        locationChooseButton.setVisible(true);
        locationLabel.setLayoutY(283);
        locationChooseButton.setLayoutY(316);
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
        field4.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                Validator.lengthConstraint(field4,12);
                Validator.numberConstraint(field4);
            }
        });
        ProviderEntity providerEntity=ServiceList.organizationsService.selectByIdPr(id);
        field1.setText(providerEntity.getOrganizationByOrganizationId().getNameOfOrganization());
        field2.setText(providerEntity.getOrganizationByOrganizationId().getPhone());
        field3.setText(providerEntity.getOrganizationByOrganizationId().getMail());
        field4.setText(providerEntity.getItn());
        locID=providerEntity.getLocationId();
        LocationEntity loc=ServiceList.locationService.selectById(locID);
        locationLabel.setText(loc.getCountry()+", "+loc.getCity()+", "+loc.getStreet()+", "+loc.getHouse());

    }
    public void supplyFill(){
        label1.setText("Total price");
        label2.setText("Quantity");
        label5.setText("Provider");
        label3.setText("CD");
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label5.setVisible(true);
        field1.setVisible(true);
        field2.setVisible(true);
        providerLabel.setVisible(true);
        providerChooseButton.setVisible(true);
        cdLabel.setVisible(true);
        cdChooseButton.setVisible(true);
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
        SupplyEntity supplyEntity=ServiceList.supplyService.selectById(id);
        field1.setText(String.valueOf(supplyEntity.getTotalPrice()));
        field2.setText(String.valueOf(supplyEntity.getQuantity()));
        prID=supplyEntity.getOrganizationId();
        cdID=supplyEntity.getCdId();
        CdEntity cd=ServiceList.cdService.selectById(cdID);
        cdLabel.setText(cd.getAlbum()+", "+cd.getArtistByArtistId().getName());
        ProviderEntity prov=ServiceList.organizationsService.selectByIdPr(prID);
        providerLabel.setText(prov.getOrganizationByOrganizationId().getNameOfOrganization()+", "+prov.getOrganizationByOrganizationId().getMail());

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
        String regmode="";
        try {
            if (mode != null) {
                switch (mode) {
                    case ("Artist"):
                        ArtistEntity entity = ServiceList.artistService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Name should contain at least 2 symbols");
                        if(field2.getText().length()==0) throw new Exception("Number of albums should be non-empty");
                        entity.setName(field1.getText());
                        entity.setNumberOfAlbums(Integer.parseInt(field2.getText()));
                        ServiceList.artistService.update(entity);
                        break;
                    case ("Booking"):
                        BookingEntity bookingEntity = ServiceList.bookingService.selectById(id);
                        if(field1.getText().length()<1) throw new Exception("Name can't be null");
                        if(field2.getText().length()<1) throw new Exception("LName can't be null");
                        if(field3.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        bookingEntity.setDate(new Date(System.currentTimeMillis()));
                        int customer =ServiceList.customerService.selectByName(field1.getText(),field2.getText(),field3.getText());
                        if(customer!=-1) {
                            bookingEntity.setCustomerId(customer);
                            ServiceList.bookingService.update(bookingEntity);
                        }
                        else{
                            regmode="Customer";
                            throw new Exception("Customer is absent, register new one");
                        }
                        break;
                    case ("Booking Position"):
                        BookingPositionEntity bookingPositionEntity = ServiceList.bookingService.getBookingPosByComposite(id, id2);
                        if(field3.getText().length()==0) throw new Exception("Quantity can't be null");
                        if(consID==0) throw new Exception("Consignment should be non-empty");
                        //bookingPositionEntity.setBookingId(Integer.parseInt(field1.getText()));
                        bookingPositionEntity.setBookingId(id);
                        bookingPositionEntity.setConsignmentId(consID);
                        bookingPositionEntity.setQuantity(Integer.parseInt(field3.getText()));
                        ServiceList.bookingService.updatePosition(bookingPositionEntity);
                        break;
                    case ("CD"):
                        CdEntity cdEntity = ServiceList.cdService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Name of album should contain at least 2 symbols");
                        if(genre.getSelectionModel().getSelectedItem()==null) throw new Exception("Choose genre");
                        if(artID==0) throw new Exception("Artist can't be null");
                        if(labelID==0) throw new Exception("Music Label can't be null");
                        cdEntity.setAlbum(field1.getText());
                        cdEntity.setGenre(genre.getValue());
                        cdEntity.setArtistId(artID);
                        cdEntity.setOrganizationId(labelID);
                        ServiceList.cdService.update(cdEntity);
                        break;
                    case ("Consignment"):
                        ConsignmentEntity consignmentEntity = ServiceList.consignmentService.selectById(id);
                        if(field1.getText().length()<1) throw new Exception("Quantity can't be null");
                        if(cdID==0) throw new Exception("CD can't be null");
                        if(prID==0) throw new Exception("Provider can't be null");
                        if(field2.getText().length()<1) throw new Exception("Price can't be null");
                        consignmentEntity.setQuantity(Integer.parseInt(field1.getText()));
                        consignmentEntity.setCdId(cdID);
                        consignmentEntity.setOrganizationId(prID);
                        consignmentEntity.setPrice(Integer.parseInt(field2.getText()));
                        ServiceList.consignmentService.update(consignmentEntity);
                        break;
                    case ("Customer"):
                        CustomerEntity customerEntity = ServiceList.customerService.selectById(id);
                        if(field1.getText().length()<1) throw new Exception("Name can't be null");
                        if(field2.getText().length()<1) throw new Exception("LName can't be null");
                        if(field3.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field4.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        if(field5.getText().length()==0) throw new Exception("Age can't be null");
                        if(locID==0) throw new Exception("Location can't be null");
                        customerEntity.setName(field1.getText());
                        customerEntity.setSurname(field2.getText());
                        customerEntity.setPhone(field3.getText());
                        customerEntity.setMail(field4.getText());
                        customerEntity.setAge(Integer.parseInt(field5.getText()));
                        customerEntity.setLocationId(locID);
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
                   /* case ("Organizations"):
                        OrganizationEntity organizationEntity = ServiceList.organizationsService.selectById(id);
                        if(field1.getText().length()<2) throw new Exception("Name of organization should contain at least 2 symbols");
                        if(field2.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field3.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        organizationEntity.setNameOfOrganization(field1.getText());
                        organizationEntity.setPhone(field2.getText());
                        organizationEntity.setMail(field3.getText());
                        ServiceList.organizationsService.update(organizationEntity);
                        break;*/
                    case ("Music Label"):
                        MusicLabelEntity musicLabelEntity = ServiceList.organizationsService.selectByIdML(id);
                        OrganizationEntity organizationEntity=ServiceList.organizationsService.selectById(musicLabelEntity.getOrganizationId());
                        if(field1.getText().length()<2) throw new Exception("Name of organization should contain at least 2 symbols");
                        if(field2.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field3.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        if(field4.getText().length()<1) throw new Exception("Record studio amount can't be null");
                        organizationEntity.setNameOfOrganization(field1.getText());
                        organizationEntity.setPhone(field2.getText());
                        organizationEntity.setMail(field3.getText());
                        ServiceList.organizationsService.update(organizationEntity);
                        //musicLabelEntity.setOrganizationId(ServiceList.organizationsService.selectByFilter(organizationEntity.getNameOfOrganization(),organizationEntity.getPhone(),organizationEntity.getMail()));
                        musicLabelEntity.setStudioAmount(Integer.parseInt(field4.getText()));
                        ServiceList.organizationsService.updateML(musicLabelEntity);
                        break;
                    case ("Provider"):
                        ProviderEntity providerEntity = ServiceList.organizationsService.selectByIdPr(id);
                        OrganizationEntity organizationEntity1=ServiceList.organizationsService.selectById(providerEntity.getOrganizationId());
                        if(field1.getText().length()<2) throw new Exception("Name of organization should contain at least 2 symbols");
                        if(field2.getText().length()<11) throw new Exception("Phone number should contain from 11 to 13 symbols in international format");
                        if(field3.getText().length()<9) throw new Exception("EMail should contain at least 9 symbols");
                        if(locID==0) throw new Exception("Location can't be null");
                        if(field4.getText().length()<10) throw new Exception("ITN should contain from 10 to 12 numbers");
                        organizationEntity1.setNameOfOrganization(field1.getText());
                        organizationEntity1.setPhone(field2.getText());
                        organizationEntity1.setMail(field3.getText());
                        ServiceList.organizationsService.update(organizationEntity1);
                        providerEntity.setOrganizationId(ServiceList.organizationsService.selectByFilter(organizationEntity1.getNameOfOrganization(),organizationEntity1.getPhone(),organizationEntity1.getMail()));
                        providerEntity.setLocationId(locID);
                        providerEntity.setItn(field4.getText());
                        ServiceList.organizationsService.updatePr(providerEntity);
                        break;
                    case ("Supply"):
                        SupplyEntity supplyEntity = ServiceList.supplyService.selectById(id);
                        if(field1.getText().length()<1)throw new Exception("Total Price can't be null");
                        if(field2.getText().length()<1) throw new Exception("Quantity can't be null");
                        if(prID==0) throw new Exception("Provider can't be null");
                        if(cdID==0) throw new Exception("CD can't be null");
                        supplyEntity.setDate(new Date(System.currentTimeMillis()));
                        supplyEntity.setTotalPrice(Double.parseDouble(field1.getText()));
                        supplyEntity.setQuantity(Integer.parseInt(field2.getText()));
                        supplyEntity.setOrganizationId(prID);
                        supplyEntity.setCdId(cdID);
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
            if(!regmode.equals("")){
                register(regmode);
            }
        }
    }
    public void register(String regmode){
        String tempmode=mode;
        try {
            ModalWindow modal = new ModalWindow();
            modal.newWindow("Adding", regmode, "");
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Something goes wrong");
            alert.setContentText("Check correctness of your choice");
            alert.showAndWait();
        }
        mode=tempmode;
    }
    public void cancel(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void consignmentChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("Consignment","Edit");
            ConsignmentEntity cons=ServiceList.consignmentService.selectById(consID);
            consignmentLabel.setText(cons.getCdByCdId().getAlbum()+";  "+cons.getProviderByOrganizationId().getOrganizationByOrganizationId().getNameOfOrganization());
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
    public void locationChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("Location","Edit");
            LocationEntity loc=ServiceList.locationService.selectById(locID);
            locationLabel.setText(loc.getCountry()+", "+loc.getCity()+", "+loc.getStreet()+", "+loc.getHouse());
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
    public void artistChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("Artist","Edit");
            ArtistEntity art=ServiceList.artistService.selectById(artID);
            artistLabel.setText(art.getName()+", "+art.getNumberOfAlbums()+" albums");
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
    public void labelChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("Music Label","Edit");
            MusicLabelEntity mus=ServiceList.organizationsService.selectByIdML(labelID);
            musicLabel.setText(mus.getOrganizationByOrganizationId().getNameOfOrganization()+", "+mus.getOrganizationByOrganizationId().getMail());
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
    public void cdChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("CD","Edit");
            CdEntity cd=ServiceList.cdService.selectById(cdID);
            cdLabel.setText(cd.getAlbum()+", "+cd.getArtistByArtistId().getName());
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
    public void providerChoose(ActionEvent e){
        String tempmode=mode;
        try {
            ChoiceWindow modal = new ChoiceWindow();
            modal.newWindow("Provider","Edit");
            ProviderEntity prov=ServiceList.organizationsService.selectByIdPr(prID);
            providerLabel.setText(prov.getOrganizationByOrganizationId().getNameOfOrganization()+", "+prov.getOrganizationByOrganizationId().getMail());
        } catch (Exception ex) {

        }
        finally {
            mode=tempmode;
        }
    }
}
