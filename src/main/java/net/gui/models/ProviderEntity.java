package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "provider", schema = "cdshop", catalog = "")
public class ProviderEntity {
    private int organizationId;
    private String itn;
    private int locationId;
    private Collection<ConsignmentEntity> consignmentsByOrganizationId;
    private LocationEntity locationByLocationId;
    private OrganizationEntity organizationByOrganizationId;
    private Collection<SupplyEntity> suppliesByOrganizationId;

    @Id
    @Column(name = "Organization_ID", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "ITN", nullable = false, length = 12)
    public String getItn() {
        return itn;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProviderEntity that = (ProviderEntity) o;

        if (organizationId != that.organizationId) return false;
        if (itn != null ? !itn.equals(that.itn) : that.itn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId;
        result = 31 * result + (itn != null ? itn.hashCode() : 0);
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

    @OneToMany(mappedBy = "providerByOrganizationId")
    public Collection<ConsignmentEntity> getConsignmentsByOrganizationId() {
        return consignmentsByOrganizationId;
    }

    public void setConsignmentsByOrganizationId(Collection<ConsignmentEntity> consignmentsByOrganizationId) {
        this.consignmentsByOrganizationId = consignmentsByOrganizationId;
    }

    @ManyToOne
    @JoinColumn(name = "Location_ID", referencedColumnName = "Location_ID", nullable = false,updatable=false,insertable=false)
    public LocationEntity getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(LocationEntity locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }

    @OneToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID", nullable = false,updatable=false,insertable=false)
    public OrganizationEntity getOrganizationByOrganizationId() {
        return organizationByOrganizationId;
    }

    public void setOrganizationByOrganizationId(OrganizationEntity organizationByOrganizationId) {
        this.organizationByOrganizationId = organizationByOrganizationId;
    }

    @OneToMany(mappedBy = "providerByOrganizationId")
    public Collection<SupplyEntity> getSuppliesByOrganizationId() {
        return suppliesByOrganizationId;
    }

    public void setSuppliesByOrganizationId(Collection<SupplyEntity> suppliesByOrganizationId) {
        this.suppliesByOrganizationId = suppliesByOrganizationId;
    }
}
