package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "media_type")
public class public_media_type {
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "subchapter_id", nullable = false)
    private public_media_sub_chapters subchapter_id;

    public public_media_type() {}

    public public_media_type(Long id, public_media_sub_chapters subchapter_id, String description) {
        this.description = description;
        this.id = id;
        this.subchapter_id = subchapter_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public public_media_sub_chapters getSubchapter_id() {
        return subchapter_id;
    }

    public void setSubchapter_id(public_media_sub_chapters subchapter_id) {
        this.subchapter_id = subchapter_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
