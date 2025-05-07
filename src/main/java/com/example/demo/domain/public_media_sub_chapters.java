package com.example.demo.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "media")
public class public_media_sub_chapters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long media_id;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String description;
    private String summary;
    

    @OneToMany
    @JsonBackReference
    @JoinTable(
        name = "id_subchapter",
        joinColumns = @JoinColumn(name = "subchapter_id", foreignKey = @ForeignKey(name = "fk_id_subchapter_media")),
        inverseJoinColumns = @JoinColumn(name = "subchapters_id")
    )
    private List<public_media_type> media_type = new ArrayList<>();


    public public_media_sub_chapters() {
    }


    public public_media_sub_chapters(Long media_id, Timestamp created_at, Timestamp updated_at, String description,
            String summary, List<public_media_type> media_type) {
        this.media_id = media_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.description = description;
        this.summary = summary;
        this.media_type = media_type;
    }

    
}
