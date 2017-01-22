package net.gui.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by EvSpirit on 21.12.2016.
 */
public class BookingPositionEntityPK implements Serializable {
    private int consignmentId;
    private int bookingId;

    @Column(name = "Consignment_ID", nullable = false)
    @Basic
    @Id
    public int getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(int consignmentId) {
        this.consignmentId = consignmentId;
    }

    @Column(name = "Booking_ID", nullable = false)
    @Basic
    @Id
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingPositionEntityPK that = (BookingPositionEntityPK) o;

        if (consignmentId != that.consignmentId) return false;
        if (bookingId != that.bookingId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consignmentId;
        result = 31 * result + bookingId;
        return result;
    }
}
