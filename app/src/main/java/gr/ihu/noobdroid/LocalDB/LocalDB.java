package gr.ihu.noobdroid.LocalDB;

import android.content.ContentValues;
import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.io.Serializable;

@Database(entities = {Sport.class, Sportsman.class, Team.class}, version = 1)
public abstract class LocalDB extends RoomDatabase implements Serializable {

    public abstract LocalDBInterface localDBInterface();

}
