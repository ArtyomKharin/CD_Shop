package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "cd", schema = "cdshop", catalog = "")
public class CdEntity {
    private int cdId;
    private String album;
    private String genre;
    private int artistId;
    private int organizationId;
    private ArtistEntity artistByArtistId;
    private MusicLabelEntity musicLabelByOrganizationId;
    private MusicLabelEntity musicLabelByOrganizationId_0;
    private Collection<ConsignmentEntity> consignmentsByCdId;
    private Collection<SupplyEntity> suppliesByCdId;

    @Id
    @Column(name = "CD_ID", nullable = false)
    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    @Basic
    @Column(name = "Album", nullable = false, length = 20)
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Basic
    @Column(name = "Genre", nullable = false, length = 15)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdEntity cdEntity = (CdEntity) o;

        if (cdId != cdEntity.cdId) return false;
        if (album != null ? !album.equals(cdEntity.album) : cdEntity.album != null) return false;
        if (genre != null ? !genre.equals(cdEntity.genre) : cdEntity.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cdId;
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Artist_ID", nullable = false)
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "Organization_ID", nullable = false)
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @ManyToOne
    @JoinColumn(name = "Artist_ID", referencedColumnName = "Artist_ID", nullable = false, updatable=false,insertable=false)
    public ArtistEntity getArtistByArtistId() {
        return artistByArtistId;
    }

    public void setArtistByArtistId(ArtistEntity artistByArtistId) {
        this.artistByArtistId = artistByArtistId;
    }

    @ManyToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID", nullable = false, updatable=false,insertable=false)
    public MusicLabelEntity getMusicLabelByOrganizationId() {
        return musicLabelByOrganizationId;
    }

    public void setMusicLabelByOrganizationId(MusicLabelEntity musicLabelByOrganizationId) {
        this.musicLabelByOrganizationId = musicLabelByOrganizationId;
    }

    @ManyToOne
    @JoinColumn(name = "Organization_ID", referencedColumnName = "Organization_ID", nullable = false,updatable=false,insertable=false)
    public MusicLabelEntity getMusicLabelByOrganizationId_0() {
        return musicLabelByOrganizationId_0;
    }

    public void setMusicLabelByOrganizationId_0(MusicLabelEntity musicLabelByOrganizationId_0) {
        this.musicLabelByOrganizationId_0 = musicLabelByOrganizationId_0;
    }

    @OneToMany(mappedBy = "cdByCdId")
    public Collection<ConsignmentEntity> getConsignmentsByCdId() {
        return consignmentsByCdId;
    }

    public void setConsignmentsByCdId(Collection<ConsignmentEntity> consignmentsByCdId) {
        this.consignmentsByCdId = consignmentsByCdId;
    }

    @OneToMany(mappedBy = "cdByCdId")
    public Collection<SupplyEntity> getSuppliesByCdId() {
        return suppliesByCdId;
    }

    public void setSuppliesByCdId(Collection<SupplyEntity> suppliesByCdId) {
        this.suppliesByCdId = suppliesByCdId;
    }
}
