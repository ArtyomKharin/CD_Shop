package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "music_label", schema = "cdshop", catalog = "")
public class MusicLabelEntity {
    private int organizationId;
    private int studioAmount;
    private Collection<CdEntity> cdsByOrganizationId;
    private Collection<CdEntity> cdsByOrganizationId_0;
    private OrganizationEntity organizationByOrganizationId;

    @Id
    @Column(name = "Organization_ID", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "Studio_amount", nullable = false)
    public int getStudioAmount() {
        return studioAmount;
    }

    public void setStudioAmount(int studioAmount) {
        this.studioAmount = studioAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MusicLabelEntity that = (MusicLabelEntity) o;

        if (organizationId != that.organizationId) return false;
        if (studioAmount != that.studioAmount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId;
        result = 31 * result + studioAmount;
        return result;
    }

    @OneToMany(mappedBy = "musicLabelByOrganizationId")
    public Collection<CdEntity> getCdsByOrganizationId() {
        return cdsByOrganizationId;
    }

    public void setCdsByOrganizationId(Collection<CdEntity> cdsByOrganizationId) {
        this.cdsByOrganizationId = cdsByOrganizationId;
    }

    @OneToMany(mappedBy = "musicLabelByOrganizationId_0")
    public Collection<CdEntity> getCdsByOrganizationId_0() {
        return cdsByOrganizationId_0;
    }

    public void setCdsByOrganizationId_0(Collection<CdEntity> cdsByOrganizationId_0) {
        this.cdsByOrganizationId_0 = cdsByOrganizationId_0;
    }

    @OneToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID", nullable = false,updatable=false,insertable=false)
    public OrganizationEntity getOrganizationByOrganizationId() {
        return organizationByOrganizationId;
    }

    public void setOrganizationByOrganizationId(OrganizationEntity organizationByOrganizationId) {
        this.organizationByOrganizationId = organizationByOrganizationId;
    }
}
