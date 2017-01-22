package net.gui.models;

import javax.persistence.*;

/**
 * Created by EvSpirit on 21.12.2016.
 */
@Entity
@Table(name = "booking_position", schema = "cdshop", catalog = "")
@IdClass(BookingPositionEntityPK.class)
public class BookingPositionEntity {
    private int quantity;
    private int consignmentId;
    private int bookingId;
    private ConsignmentEntity consignmentByConsignmentId;
    private BookingEntity bookingByBookingId;

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingPositionEntity that = (BookingPositionEntity) o;

        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return quantity;
    }

    @Id
    @Basic
    @Column(name = "Consignment_ID", nullable = false)
    public int getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(int consignmentId) {
        this.consignmentId = consignmentId;
    }

    @Id
    @Basic
    @Column(name = "Booking_ID", nullable = false)
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @ManyToOne
    @JoinColumn(name = "Consignment_ID", referencedColumnName = "Consignment_ID", nullable = false,updatable=false,insertable=false)
    public ConsignmentEntity getConsignmentByConsignmentId() {
        return consignmentByConsignmentId;
    }

    public void setConsignmentByConsignmentId(ConsignmentEntity consignmentByConsignmentId) {
        this.consignmentByConsignmentId = consignmentByConsignmentId;
    }

    @ManyToOne
    @JoinColumn(name = "Booking_ID", referencedColumnName = "Booking_ID", nullable = false,updatable=false,insertable=false)
    public BookingEntity getBookingByBookingId() {
        return bookingByBookingId;
    }

    public void setBookingByBookingId(BookingEntity bookingByBookingId) {
        this.bookingByBookingId = bookingByBookingId;
    }
}
