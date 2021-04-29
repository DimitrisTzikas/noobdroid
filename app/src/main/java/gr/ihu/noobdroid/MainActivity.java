package gr.ihu.noobdroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}