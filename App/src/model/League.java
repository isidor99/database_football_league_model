package model;

public class League {

    private int id;
    private String leagueName;
    private String country;

    public League() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", leagueName='" + leagueName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
