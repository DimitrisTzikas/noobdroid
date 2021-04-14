package gr.ihu.noobdroid.LocalDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "teams")
public class Team {

    @PrimaryKey
    @ColumnInfo (name = "team_id")
    private int id;
    @ColumnInfo (name = "team_name")
    private String name;
    @ColumnInfo (name = "team_stadium")
    private String stadium;
    @ColumnInfo (name = "team_headquarters")
    private String headquarters;
    @ColumnInfo (name = "team_country")
    private String country;
    @ColumnInfo (name = "team_sport_id")
    private int sportId;
    @ColumnInfo (name = "team_establish_year")
    private int establishYear;

    public Team(int id, String name, String stadium, String headquarters, String country,
                int sportId, int establishYear) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
        this.headquarters = headquarters;
        this.country = country;
        this.sportId = sportId;
        this.establishYear = establishYear;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public void setEstablishYear(int establishYear) {
        this.establishYear = establishYear;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getStadium() { return this.stadium; }

    public String getHeadquarters() { return this.headquarters; }

    public String getCountry() { return this.country; }

    public int getSportId() { return this.sportId; }

    public int getEstablishYear() { return this.establishYear; }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stadium='" + stadium + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", country='" + country + '\'' +
                ", sportId=" + sportId +
                ", establishYear=" + establishYear +
                '}';
    }

}
