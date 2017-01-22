package net.gui.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "booking", schema = "cdshop", catalog = "")
public class BookingEntity {
    private int bookingId;
    private Date date;
    private int totalPrice;
    private boolean finished;
    private int customerId;
    private CustomerEntity customerByCustomerId;
    private Collection<BookingPositionEntity> bookingPositionsByBookingId;

    @Id
    @Column(name = "Booking_ID", nullable = false)
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Basic
    @Column(name = "Date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Total_Price", nullable = false)
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "Finished", nullable = false)
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingEntity that = (BookingEntity) o;

        if (bookingId != that.bookingId) return false;
        if (totalPrice != that.totalPrice) return false;
        if (finished != that.finished) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookingId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + totalPrice;
        result = 31 * result + (finished ? 1 : 0);
        return result;
    }

    @Basic
    @Column(name = "Customer_ID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @ManyToOne
    @JoinColumn(name = "Customer_ID", referencedColumnName = "Customer_ID", nullable = false,updatable=false,insertable=false)
    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @OneToMany(mappedBy = "bookingByBookingId")
    public Collection<BookingPositionEntity> getBookingPositionsByBookingId() {
        return bookingPositionsByBookingId;
    }

    public void setBookingPositionsByBookingId(Collection<BookingPositionEntity> bookingPositionsByBookingId) {
        this.bookingPositionsByBookingId = bookingPositionsByBookingId;
    }
}
