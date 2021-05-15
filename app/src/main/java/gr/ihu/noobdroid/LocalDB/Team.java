package gr.ihu.noobdroid.LocalDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    @ColumnInfo (name = "location_x")
    private int locationX;
    @ColumnInfo (name = "location_y")
    private int locationY;
    @ColumnInfo (name = "team_country")
    private String country;
    @ColumnInfo (name = "team_sport_id")
    private int sportId;
    @ColumnInfo (name = "team_establish_year")
    private int establishYear;
    @ColumnInfo (name = "team_players_ids")
    private String teamPlayersIDs;

    public Team(int id, String name, String stadium, String headquarters, int locationX, int locationY, String country,
                int sportId, int establishYear) {
        this.id = id;
        this.name = name;
        this.stadium = stadium;
        this.headquarters = headquarters;
        this.locationX = locationX;
        this.locationY = locationY;
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

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
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

    public int getLocationX() {
        return this.locationX;
    }

    public int getLocationY() {
        return this.locationY;
    }

    public String getCountry() { return this.country; }

    public int getSportId() { return this.sportId; }

    public int getEstablishYear() { return this.establishYear; }

    public String getTeamPlayersIDs() {
        return this.teamPlayersIDs;
    }

    public boolean hasPlayer(int playerID) {
        ArrayList<Integer> playersIDs = this.getTeamPlayersID();
        for (int i = 0; i < playersIDs.size(); i++) {
            if (playersIDs.get(i) == playerID) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getTeamPlayersID() {
        if (this.teamPlayersIDs.equals("")) {
            return new ArrayList<Integer>();
        }
        else {
            List<String> temp = Arrays.asList(this.teamPlayersIDs.split(","));
            ArrayList<Integer> intTemp = new ArrayList<Integer>();

            for (int i = 0; i < temp.size(); i++) {
                if (!temp.get(i).equals("")) {
                    intTemp.add(Integer.parseInt(temp.get(i)));
                }
            }

            return intTemp;
        }
    }

    public void addPlayer(int sportsmanId) {
        this.teamPlayersIDs += String.valueOf(sportsmanId) + ",";
    }

    public void removePlayer(int sportsmanId) {
        List<String> temp = Arrays.asList(this.teamPlayersIDs.split(","));
        String tempString = "";
        for (int i = 0; i < temp.size(); i++) {
            if (!temp.get(i).equals(String.valueOf(sportsmanId))) {
                tempString += temp.get(i) + ",";
            }
        }
        this.teamPlayersIDs = tempString;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name=" + name + '\n' +
                ", stadium=" + stadium + '\n' +
                ", headquarters=" + headquarters + '\n' +
                ", locationX=" + locationX +
                ", locationY=" + locationY +
                ", country=" + country + '\n' +
                ", sportId=" + sportId +
                ", establishYear=" + establishYear +
                '}';
    }

}
