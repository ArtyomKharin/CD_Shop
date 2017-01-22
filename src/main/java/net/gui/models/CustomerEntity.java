package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "customer", schema = "cdshop", catalog = "")
public class CustomerEntity {
    private int customerId;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private int age;
    private int locationId;
    private Collection<BookingEntity> bookingsByCustomerId;
    private LocationEntity locationByLocationId;

    @Id
    @Column(name = "Customer_ID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Surname", nullable = false, length = 20)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Phone", nullable = false, length = 13)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "E_mail", nullable = true, length = 32)
    public String getMail() {
        return mail;
    }

        public void setMail(String mail) {
        this.mail = mail;
    }
    @Basic
    @Column(name = "Age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerId != that.customerId) return false;
        if (age != that.age) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;

        return true;
    }

@Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Basic
    @Column(name = "Location_ID", nullable = false)
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }


    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<BookingEntity> getBookingsByCustomerId() {
        return bookingsByCustomerId;
    }

    public void setBookingsByCustomerId(Collection<BookingEntity> bookingsByCustomerId) {
        this.bookingsByCustomerId = bookingsByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "Location_ID", referencedColumnName = "Location_ID", nullable = false,updatable=false,insertable=false)
    public LocationEntity getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationEntity locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }
}
