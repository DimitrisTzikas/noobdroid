package gr.ihu.noobdroid.LocalDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "sportsman")
public class Sportsman {

    @PrimaryKey
    @ColumnInfo (name = "sportsman_id")
    private int id;
    @ColumnInfo (name = "sportsman_first_name")
    private String firstName;
    @ColumnInfo (name = "sportsman_last_name")
    private String lastName;
    @ColumnInfo (name = "sportsman_headquarters")
    private String headquarters;
    @ColumnInfo (name = "sportsman_country")
    private String country;
    @ColumnInfo (name = "sportsman_sportId")
    private int sportId;
    @ColumnInfo (name = "sportsman_birth_year")
    private int birthYear;

    public Sportsman(int id, String firstName, String lastName, String headquarters, String country,
                     int sportId, int birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headquarters = headquarters;
        this.country = country;
        this.sportId = sportId;
        this.birthYear = birthYear;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() { return this.id; }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getHeadquarters() { return this.headquarters; }

    public String getCountry() { return this.country; }

    public int getSportId() { return this.sportId; }

    public int getBirthYear() { return this.birthYear; }

    @Override
    public String toString() {
        return "Sportsman{" +
                "  id=" + this.id +
                "  , firstName=" + this.firstName +
                "  , lastName=" + this.lastName +
                "  , headquarters=" + this.headquarters +
                "  , country=" + this.country +
                "  , sportId=" + this.sportId +
                "  , birthYear=" + this.birthYear +
                "}";
    }
    
}
