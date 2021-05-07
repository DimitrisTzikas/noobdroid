package gr.ihu.noobdroid.LocalDB;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.util.StringUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @ColumnInfo (name = "team_players_ids")
    private String teamPlayersIDs;

    public Team(int id, String name, String stadium, String headquarters, String country,
                int sportId, int establishYear) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
        this.headquarters = headquarters;
        this.country = country;
        this.sportId = sportId;
        this.establishYear = establishYear;
        this.teamPlayersIDs = "";
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

    public void setTeamPlayersIDs(String teamPlayersIDs) {
        this.teamPlayersIDs = teamPlayersIDs;
    }

    public void setTeamPlayersID(ArrayList<Integer> teamPlayersIDs) {
        this.teamPlayersIDs = "";
        for (int i = 0; i < teamPlayersIDs.size(); i++) {
            this.teamPlayersIDs += String.valueOf(teamPlayersIDs.get(i)) + ",";
        }
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getStadium() { return this.stadium; }

    public String getHeadquarters() { return this.headquarters; }

    public String getCountry() { return this.country; }

    public int getSportId() { return this.sportId; }

    public int getEstablishYear() { return this.establishYear; }

    public String getTeamPlayersIDs() {
        return this.teamPlayersIDs;
    }

    public ArrayList<Integer> getTeamPlayersID() {
        if (this.teamPlayersIDs.equals("")) {
            return new ArrayList<Integer>();
        }
        else {
            ArrayList<String> temp = (ArrayList<String>) Arrays.asList(this.teamPlayersIDs.split(","));
            temp.remove(temp.size() - 1);
            ArrayList<Integer> intTemp = new ArrayList<Integer>();

            for (int i = 0; i < temp.size(); i++) {
                intTemp.add(Integer.parseInt(temp.get(i)));
            }

            return intTemp;
        }
    }

    public void addPlayer(int sportsmanId) {
        this.teamPlayersIDs += String.valueOf(sportsmanId) + ",";
    }

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
