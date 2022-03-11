package model;

import java.sql.Blob;

public class Club {

    private int id;
    private String name;
    private Blob emblem;
    private int stadiumId;
    private String stadiumName;
    private int stadiumCapacity;
    private int cityId;
    private String city;
    private String country;

    public Club() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getEmblem() {
        return emblem;
    }

    public void setEmblem(Blob emblem) {
        this.emblem = emblem;
    }

    public int getStadiumId() { return stadiumId; }

    public void setStadiumId(int stadiumId) { this.stadiumId = stadiumId; }

    public String getStadiumName() { return stadiumName; }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public int getStadiumCapacity() {
        return stadiumCapacity;
    }

    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public int getCityId() { return cityId; }

    public void setCityId(int cityId) { this.cityId = cityId; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;
    }
}
