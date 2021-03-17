package gr.ihu.noobdroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LocalDB localDB = new LocalDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sport sport = new Sport(1, "Formula", true, true);
        localDB.add(sport);
    }

}
