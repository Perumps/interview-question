package com.example.demo.repository;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * URL entity that includes:
 *
 * 1) shortUrl - Hashed value of long URL that acts as the id of the table
 * 2) longUrl - Original long URL provided as user input
 * 3) createdTimestamp - Time when the database entry was created (used to apply purge rules)
 *
 */
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

    /**
     * Overrides toString method
     *
     * @return String representation of the object
     *
     */
    @Override
    public String toString() {
        return "UrlEntity [shortUrl=" + shortUrl +
                ", longUrl=" + longUrl + ", createdTimestamp=" + createdTimestamp + "]";
    }
}
