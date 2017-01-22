package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "consignment", schema = "cdshop", catalog = "")
public class ConsignmentEntity {
    private int consignmentId;
    private int quantity;
    private int price;
    private int cdId;
    private int organizationId;
    private Collection<BookingPositionEntity> bookingPositionsByConsignmentId;
    private CdEntity cdByCdId;
    private ProviderEntity providerByOrganizationId;

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Id
    @Column(name = "Consignment_ID", nullable = false)
    public int getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(int consignmentId) {
        this.consignmentId = consignmentId;
    }

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsignmentEntity that = (ConsignmentEntity) o;

        if (consignmentId != that.consignmentId) return false;
        if (quantity != that.quantity) return false;
        if (price != that.price) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consignmentId;
        result = 31 * result + quantity;
        result = 31 * result + price;
        return result;
    }

    @Basic
    @Column(name = "CD_ID", nullable = false)
    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    @Basic
    @Column(name = "Organization_ID", nullable = true)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @OneToMany(mappedBy = "consignmentByConsignmentId")
    public Collection<BookingPositionEntity> getBookingPositionsByConsignmentId() {
        return bookingPositionsByConsignmentId;
    }

    public void setBookingPositionsByConsignmentId(Collection<BookingPositionEntity> bookingPositionsByConsignmentId) {
        this.bookingPositionsByConsignmentId = bookingPositionsByConsignmentId;
    }

    @ManyToOne
    @JoinColumn(name = "CD_ID", referencedColumnName = "CD_ID", nullable = false,updatable=false,insertable=false)
    public CdEntity getCdByCdId() {
        return cdByCdId;
    }

    public void setCdByCdId(CdEntity cdByCdId) {
        this.cdByCdId = cdByCdId;
    }

    @ManyToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID",updatable=false,insertable=false)
    public ProviderEntity getProviderByOrganizationId() {
        return providerByOrganizationId;
    }

    public void setProviderByOrganizationId(ProviderEntity providerByOrganizationId) {
        this.providerByOrganizationId = providerByOrganizationId;
    }
}
