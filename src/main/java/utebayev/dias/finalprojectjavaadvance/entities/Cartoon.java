package utebayev.dias.finalprojectjavaadvance.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cartoon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private String country;
    private String genres;
    private String actors;
    private int time;
    private String linkToCartoon;
    private String linkToImage;
    private int genreId;

    public Cartoon(){}

    public Cartoon(String name, int year, String country, String genres, String actors, int time, String linkToCartoon, String linkToImage, int genreId) {
        this.name = name;
        this.year = year;
        this.country = country;
        this.genres = genres;
        this.actors = actors;
        this.time = time;
        this.linkToCartoon = linkToCartoon;
        this.linkToImage = linkToImage;
        this.genreId = genreId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLinkToCartoon() {
        return linkToCartoon;
    }

    public void setLinkToCartoon(String linkToCartoon) {
        this.linkToCartoon = linkToCartoon;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}

