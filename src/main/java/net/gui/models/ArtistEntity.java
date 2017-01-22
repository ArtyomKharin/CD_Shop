package net.gui.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by EvSpirit on 20.12.2016.
 */
@Entity
@Table(name = "artist", schema = "cdshop", catalog = "")
public class ArtistEntity {
    private int artistId;
    private String name;
    private int numberOfAlbums;
    private Collection<CdEntity> cdsByArtistId;

    @Id
    @Column(name = "Artist_ID", nullable = false)
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
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
    @Column(name = "Number_of_albums", nullable = false)
    public int getNumberOfAlbums() {
        return numberOfAlbums;
    }

    public void setNumberOfAlbums(int numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistEntity that = (ArtistEntity) o;

        if (artistId != that.artistId) return false;
        if (numberOfAlbums != that.numberOfAlbums) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artistId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + numberOfAlbums;
        return result;
    }

    @OneToMany(mappedBy = "artistByArtistId")
    public Collection<CdEntity> getCdsByArtistId() {
        return cdsByArtistId;
    }

    public void setCdsByArtistId(Collection<CdEntity> cdsByArtistId) {
        this.cdsByArtistId = cdsByArtistId;
    }
}
