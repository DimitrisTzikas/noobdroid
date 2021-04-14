package gr.ihu.noobdroid.LocalDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "sports")
public class Sport {

    @PrimaryKey
    @ColumnInfo (name = "sport_id")
    private int id;
    @ColumnInfo (name = "sport_name")
    private String name;
    @ColumnInfo (name = "is_team_game")
    private boolean isTeamGame;
    @ColumnInfo (name = "is_male_game")
    private boolean isMaleGame;

    public Sport(int id, String name, boolean isTeamGame, boolean isMaleGame) {
        this.id = id;
        this.name = name;
        this.isTeamGame = isTeamGame;
        this.isMaleGame = isMaleGame;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamGame(boolean teamGame) {
        isTeamGame = teamGame;
    }

    public void setMaleGame(boolean maleGame) {
        isMaleGame = maleGame;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public boolean isTeamGame() { return this.isTeamGame; }

    public boolean isMaleGame() { return this.isMaleGame; }

    @Override
    public String toString() {
        return "Sport{" +
                "  id=" + this.id +
                "  , name=" + this.name +
                "  , isTeamGame" + this.isTeamGame +
                "  , isMaleGame" + this.isMaleGame +
                "}";
    }

}
