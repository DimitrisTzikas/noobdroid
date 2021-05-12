package gr.ihu.noobdroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoast.StyleableToast;

import gr.ihu.noobdroid.firebase.Race;

public class RaceFragment extends Fragment {

    public FirebaseFirestore db;
    private int rid,sid;
    private String day,city,country;

    public int selectedRaceId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();

        CollectionReference dbRace = db.collection("Race");
        Race race = new Race(sid,day,city,country);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race, container, false);


        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonDisplay = view.findViewById(R.id.btn_display);
        Button buttonDelete = view.findViewById(R.id.btn_delete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_raceFragment_to_raceInsertFragment);

            }
        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_raceFragment_to_raceDetailsActivity);

                //Intent i = new Intent(RaceFragment.this, RaceDetailsActivity.class);
                //startActivity(i);

//                if(selectedRaceId == -1){
//                    new StyleableToast
//                            .Builder(getContext())
//                            .text("Nothing to modify")
//                            .textColor(Color.WHITE)
//                            .backgroundColor(Color.GRAY)
//                            .show();
//                }
//                else {
//                    //RaceFragmentDirections.ActionRaceFragmentToRaceInsertFragment action;
//                   // action = RaceFragmentDirections.actionRaceFragmentToRaceInsertFragment(db,selectedRaceId);
//                   // Navigation.findNavController(v).navigate(action);
//                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(selectedRaceId == -1){
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to delete")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else  {
                    /////////////////////////////////
                    new StyleableToast
                            .Builder(getContext())
                            .text("Race deleted")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();
                }*/

            }
        });

        return view;
    }
}