package com.example.demo.domain;

import java.sql.Date;
import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "test_config")
public class test_config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date end_date;
    private boolean is_active;
    private int score;
    private Date start_date;

    @NotNull
    @Length(min = 1, max = 60)
    private String test_summary;

    @NotNull
    @Size(max = 50)
    private String description;


    public test_config() {}

    public test_config(Long id, Date end_date, boolean is_active, int score, Date start_date,
            @NotNull @Length(min = 1, max = 60) String test_summary, @NotNull @Size(max = 50) String description) {
        this.id = id;
        this.end_date = end_date;
        this.is_active = is_active;
        this.score = score;
        this.start_date = start_date;
        this.test_summary = test_summary;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getTest_summary() {
        return test_summary;
    }

    public void setTest_summary(String test_summary) {
        this.test_summary = test_summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
