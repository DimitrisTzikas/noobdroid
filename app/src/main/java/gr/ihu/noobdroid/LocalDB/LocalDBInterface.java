package gr.ihu.noobdroid.LocalDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocalDBInterface {

    @Insert
    public void addSport(Sport sport);

    @Query("select * from sports")
    public List<Sport> getSports();

    @Delete
    public void deleteSport(Sport sport);

    @Update
    public void updateSport(Sport sport);

}
