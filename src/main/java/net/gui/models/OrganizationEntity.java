package net.gui.models;

import javax.persistence.*;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "organization", schema = "cdshop", catalog = "")
public class OrganizationEntity {
    private int organizationId;
    private String nameOfOrganization;
    private String phone;
    private String mail;
    private MusicLabelEntity musicLabelByOrganizationId;
    private ProviderEntity providerByOrganizationId;

    @Id
    @Column(name = "Organization_ID", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "Name_of_organization", nullable = true, length = 30)
    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    @Basic
    @Column(name = "Phone", nullable = true, length = 13)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationEntity that = (OrganizationEntity) o;

        if (organizationId != that.organizationId) return false;
        if (nameOfOrganization != null ? !nameOfOrganization.equals(that.nameOfOrganization) : that.nameOfOrganization != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId;
        result = 31 * result + (nameOfOrganization != null ? nameOfOrganization.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "organizationByOrganizationId")
    public MusicLabelEntity getMusicLabelByOrganizationId() {
        return musicLabelByOrganizationId;
    }

    public void setMusicLabelByOrganizationId(MusicLabelEntity musicLabelByOrganizationId) {
        this.musicLabelByOrganizationId = musicLabelByOrganizationId;
    }

    @OneToOne(mappedBy = "organizationByOrganizationId")
    public ProviderEntity getProviderByOrganizationId() {
        return providerByOrganizationId;
    }

    public void setProviderByOrganizationId(ProviderEntity providerByOrganizationId) {
        this.providerByOrganizationId = providerByOrganizationId;
    }
}
