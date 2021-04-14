package gr.ihu.noobdroid.LocalDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocalDBInterface {

    // Functions for Sports
    @Insert
    public void addSport(Sport sport);

    @Query("select * from sports")
    public List<Sport> getSports();

    @Delete
    public void deleteSport(Sport sport);

    @Update
    public void updateSport(Sport sport);


    // Functions for Sportsman
    @Insert
    public void addSportsman(Sportsman sportsman);

    @Query("select * from sportsman")
    public List<Sportsman> getSportsman();

    @Delete
    public void deleteSportsman(Sportsman sportsman);

    @Update
    public void updateSportsman(Sportsman sportsman);


    // Functions for Team
    @Insert
    public void addTeam(Team team);

    @Query("select * from teams")
    public List<Team> getTeams();

    @Delete
    public void deleteTeam(Team team);

    @Update
    public void updateTeam(Team team);

}
