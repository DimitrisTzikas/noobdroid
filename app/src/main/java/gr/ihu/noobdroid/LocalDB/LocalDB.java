package gr.ihu.noobdroid.LocalDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sport.class, Sportsman.class, Team.class}, version = 1)
public abstract class LocalDB extends RoomDatabase {

    public abstract LocalDBInterface localDBInterface();

}
