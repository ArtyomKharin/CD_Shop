package net.gui.services;

/**
 * Created by EvSpirit on 21.12.2016.
 */
public class ServiceList {
    public static ArtistService artistService=new ArtistService();
    public static UserService userService=new UserService();
    public static CdService cdService=new CdService();
    public static ConsignmentService consignmentService=new ConsignmentService();
    public static CustomerService customerService=new CustomerService();
    public static LocationService locationService=new LocationService();
    public static SupplyService supplyService=new SupplyService();
    public static BookingService bookingService=new BookingService();
    public static OrganizationsService organizationsService=new OrganizationsService();
    public static void refresh() {
        artistService = new ArtistService();
        userService = new UserService();
        cdService = new CdService();
        consignmentService = new ConsignmentService();
        customerService = new CustomerService();
        locationService = new LocationService();
        supplyService = new SupplyService();
        bookingService = new BookingService();
        organizationsService = new OrganizationsService();
    }
}
