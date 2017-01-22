package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "location", schema = "cdshop", catalog = "")
public class LocationEntity {
    private int locationId;
    private String country;
    private String city;
    private String street;
    private String house;
    private int postCode;
    private Collection<CustomerEntity> customersByLocationId;
    private Collection<ProviderEntity> providersByLocationId;

    @Id
    @Column(name = "Location_ID", nullable = false)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "Country", nullable = false, length = 20)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "City", nullable = false, length = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "Street", nullable = false, length = 20)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "House", nullable = false, length = 10)
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Basic
    @Column(name = "Post_code", nullable = false)
    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (locationId != that.locationId) return false;
        if (postCode != that.postCode) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locationId;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + postCode;
        return result;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<CustomerEntity> getCustomersByLocationId() {
        return customersByLocationId;
    }

    public void setCustomersByLocationId(Collection<CustomerEntity> customersByLocationId) {
        this.customersByLocationId = customersByLocationId;
    }

    @OneToMany(mappedBy = "locationByLocationId")
    public Collection<ProviderEntity> getProvidersByLocationId() {
        return providersByLocationId;
    }

    public void setProvidersByLocationId(Collection<ProviderEntity> providersByLocationId) {
        this.providersByLocationId = providersByLocationId;
    }
}
