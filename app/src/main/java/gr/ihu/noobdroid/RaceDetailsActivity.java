package gr.ihu.noobdroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.firebase.Race;

public class RaceDetailsActivity extends AppCompatActivity {

    private RecyclerView raceRV;
    private ArrayList<Race> raceArrayList;
    private RaceRVAdapter raceRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.racedetails);

        raceRV = findViewById(R.id.idRVRace);
        loadingDB = findViewById(R.id.idProgressBar);

        db = FirebaseFirestore.getInstance();

        raceArrayList = new ArrayList<>();
        raceRV.setHasFixedSize(true);
        raceRV.setLayoutManager(new LinearLayoutManager(this));

        raceRVAdapter = new RaceRVAdapter(raceArrayList, this);

        raceRV.setAdapter(raceRVAdapter);

        db.collection("Race").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    loadingDB.setVisibility(View.GONE);
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d : list){
                        Race r = d.toObject(Race.class);

                        raceArrayList.add(r);
                    }
                    raceRVAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(RaceDetailsActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(RaceDetailsActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}