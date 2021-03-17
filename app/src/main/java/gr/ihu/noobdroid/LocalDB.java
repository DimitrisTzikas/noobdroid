package gr.ihu.noobdroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDB extends SQLiteOpenHelper {

    public static final String TABLE_SPORT = "sport";
    public static final String TABLE_SPORTSMAN = "sportsman";
    public static final String TABLE_TEAM = "team";
    public static final String TEAM_ID = "id";
    public static final String SPORTSMAN_ID = TEAM_ID;
    public static final String SPORT_ID = SPORTSMAN_ID;
    public static final String TEAM_NAME = "name";
    public static final String SPORT_NAME = TEAM_NAME;
    public static final String SPORT_IS_TEAM_GAME = "isTeamGame";
    public static final String SPORT_IS_MALE_GAME = "isMaleGame";
    public static final String SPORTSMAN_FIRST_NAME = "firstName";
    public static final String SPORTSMAN_LAST_NAME = "lastName";
    public static final String TEAM_HEADQUARTERS = "headquarters";
    public static final String SPORTSMAN_HEADQUARTERS = TEAM_HEADQUARTERS;
    public static final String TEAM_COUNTRY = "country";
    public static final String SPORTSMAN_COUNTRY = TEAM_COUNTRY;
    public static final String TEAM_SPORT_ID = "sportId";
    public static final String SPORTSMAN_SPORT_ID = TEAM_SPORT_ID;
    public static final String SPORTSMAN_BIRTH_YEAR = "birthYear";
    public static final String TEAM_STADIUM = "stadium";
    public static final String TEAM_ESTABLISH_YEAR = "establishYear";

    public LocalDB(@Nullable Context context) {
        super(context, "localNoobdro" + SPORT_ID + "DB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSportTable = "CREATE TABLE " + TABLE_SPORT + " (" +
                SPORT_ID + " INTEGER PRIMARY KEY," +
                SPORT_NAME + " TEXT," +
                SPORT_IS_TEAM_GAME + " INTEGER," +
                SPORT_IS_MALE_GAME + " INTEGER)";

        String createSportsmanTable = "CREATE TABLE " + TABLE_SPORTSMAN + " (" +
                SPORTSMAN_ID + " INTEGER PRIMARY KEY," +
                SPORTSMAN_FIRST_NAME + " TEXT," +
                SPORTSMAN_LAST_NAME + " TEXT," +
                SPORTSMAN_HEADQUARTERS + " TEXT," +
                SPORTSMAN_COUNTRY + " TEXT," +
                SPORTSMAN_SPORT_ID + " INTEGER," +
                SPORTSMAN_BIRTH_YEAR + " INTEGER," +
                "FOREIGN KEY(" + SPORTSMAN_SPORT_ID + ") REFERENCES " + TABLE_SPORT + "(" + SPORT_ID + "))";

        String createTeamTable = "CREATE TABLE " + TABLE_TEAM + "(" +
                TEAM_ID + " INTEGER PRIMARY KEY," +
                TEAM_NAME + " TEXT," +
                TEAM_STADIUM + " TEXT," +
                TEAM_HEADQUARTERS + " TEXT," +
                TEAM_COUNTRY + " TEXT," +
                TEAM_SPORT_ID + " INTEGER," +
                TEAM_ESTABLISH_YEAR + " INTEGER," +
                "FOREIGN KEY(" + TEAM_SPORT_ID + ") REFERENCES " + TABLE_SPORT + "(" + SPORT_ID + "))";

        db.execSQL(createSportTable);
        db.execSQL(createSportsmanTable);
        db.execSQL(createTeamTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // TODO: add some validation
    public boolean add(Sport aSport) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SPORT_ID, aSport.getId());
        cv.put(SPORT_NAME, aSport.getName());
        cv.put(SPORT_IS_TEAM_GAME, aSport.isTeamGame());
        cv.put(SPORT_IS_MALE_GAME, aSport.isMaleGame());


        long insert = db.insert(TABLE_SPORT, null, cv);
        return (insert != -1);
    }

    // TODO: add some validation
    public boolean add(Sportsman sportsman) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SPORTSMAN_ID, sportsman.getId());
        cv.put(SPORTSMAN_FIRST_NAME, sportsman.getFirstName());
        cv.put(SPORTSMAN_LAST_NAME, sportsman.getLastName());
        cv.put(SPORTSMAN_HEADQUARTERS, sportsman.getHeadquarters());
        cv.put(SPORTSMAN_COUNTRY, sportsman.getCountry());
        cv.put(SPORTSMAN_SPORT_ID, sportsman.getSportId());
        cv.put(SPORTSMAN_BIRTH_YEAR, sportsman.getBirthYear());

        long insert = db.insert(TABLE_SPORTSMAN, null, cv);
        return (insert != -1);
    }

    // TODO: add some validation
    public boolean add(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TEAM_ID, team.getId());
        cv.put(TEAM_NAME, team.getName());
        cv.put(TEAM_STADIUM, team.getStadium());
        cv.put(TEAM_HEADQUARTERS, team.getHeadquarters());
        cv.put(TEAM_COUNTRY, team.getCounty());
        cv.put(TEAM_SPORT_ID, team.getSportId());
        cv.put(TEAM_ESTABLISH_YEAR, team.getEstablishYear());

        long insert = db.insert(TABLE_TEAM, null, cv);
        return (insert != -1);
    }

}
