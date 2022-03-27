package com.example.demo.repository;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="TBL_URL")
public class UrlEntity {

    @Id
    @Column(name="short_Url")
    private String shortUrl;

    @Column(name="long_Url")
    private String longUrl;

    @Column(name="created_Timestamp")
    private Long createdTimestamp;

    public UrlEntity(String shortUrl, String longUrl, Long createdTimestamp) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.createdTimestamp = createdTimestamp;
    }

    //Setters and getters

    @Override
    public String toString() {
        return "UrlEntity [shortUrl=" + shortUrl +
                ", longUrl=" + longUrl + ", createdTimestamp=" + createdTimestamp + "]";
    }
}
