package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tbl_Song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String url;
    @Column(unique = true)
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
}
