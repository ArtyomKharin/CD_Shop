package net.gui.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "supply", schema = "cdshop", catalog = "")
public class SupplyEntity {
    private int supplyId;
    private Date date;
    private double totalPrice;
    private int quantity;
    private int organizationId;
    private int cdId;
    private ProviderEntity providerByOrganizationId;
    private CdEntity cdByCdId;

    @Id
    @Column(name = "Supply_ID", nullable = false)
    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
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
    @Column(name = "Total_Price", nullable = false, precision = 0)
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

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

        SupplyEntity that = (SupplyEntity) o;

        if (supplyId != that.supplyId) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (quantity != that.quantity) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = supplyId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        return result;
    }

    @Basic
    @Column(name = "Organization_ID", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "CD_ID", nullable = false)
    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    @ManyToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID", nullable = false,updatable=false,insertable=false)
    public ProviderEntity getProviderByOrganizationId() {
        return providerByOrganizationId;
    }

    public void setProviderByOrganizationId(ProviderEntity providerByOrganizationId) {
        this.providerByOrganizationId = providerByOrganizationId;
    }

    @ManyToOne
    @JoinColumn(name = "CD_ID", referencedColumnName = "CD_ID", nullable = false,updatable=false,insertable=false)
    public CdEntity getCdByCdId() {
        return cdByCdId;
    }

    public void setCdByCdId(CdEntity cdByCdId) {
        this.cdByCdId = cdByCdId;
    }
}
