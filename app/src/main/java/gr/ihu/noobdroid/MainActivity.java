package gr.ihu.noobdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    LocalDB localDB = new LocalDB(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        item.setChecked(true);
                        displayMessage("go home");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.teams:
                        item.setChecked(true);
                        displayMessage("show teams");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.sports:
                        item.setChecked(true);
                        displayMessage("show sports");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.sportsman:
                        item.setChecked(true);
                        displayMessage("show sportsman");
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }


        });
    }
    void displayMessage(String message) {
        Toast.makeText(content:this,message, Toast.LENGTH_LONG).show();
    }

}
