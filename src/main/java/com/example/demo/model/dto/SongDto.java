package com.example.demo.model.dto;

import com.example.demo.model.Song;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SongDto {
    private Long id;
    private String name;
    private String url;
    private String uniqueID;
    private String artistNames;
    private String customHeaderImageUrl;
    private String customSongArtImageUrl;
    private String description;
    private String fullTitle;
    private String subTitle;
    private String headerImageThumbnailUrl;
    private String artistImageUrl;
    private boolean isMusic;

    public SongDto() {};
    public SongDto(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.url = song.getUrl();
        this.uniqueID = song.getUniqueID();
        this.artistNames = song.getArtistNames();
        this.customHeaderImageUrl = song.getCustomHeaderImageUrl();
        this.customSongArtImageUrl = song.getCustomSongArtImageUrl();
        this.description = song.getDescription();
        this.fullTitle = song.getFullTitle();
        this.subTitle = song.getSubTitle();
        this.headerImageThumbnailUrl = song.getHeaderImageThumbnailUrl();
        this.artistImageUrl = song.getArtistImageUrl();
        this.isMusic = song.isMusic();
    }
}
