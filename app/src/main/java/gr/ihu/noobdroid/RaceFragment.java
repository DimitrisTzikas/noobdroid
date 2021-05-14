package gr.ihu.noobdroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.firebase.Race;

public class RaceFragment extends Fragment {

    public FirebaseFirestore db;
    public CollectionReference dbRace;
    public String raceId;
    public  String race;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        dbRace = db.collection("Race");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race, container, false);

        Spinner spinner = view.findViewById(R.id.output_spinner);
        List<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        dbRace.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                    String item = documentSnapshot.getId();
                    list.add(item);
                }
                adapter.notifyDataSetChanged();
            }
        });

        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonModify = view.findViewById(R.id.btn_modify);
        Button buttonDelete = view.findViewById(R.id.btn_delete);

        buttonInsert.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_raceFragment_to_raceInsertFragment));

        buttonModify.setOnClickListener(v -> {
            raceId = spinner.getSelectedItem().toString();
            Navigation.findNavController(v).navigate(RaceFragmentDirections.actionRaceFragmentToRaceModifyFragment(raceId));
        });

        buttonDelete.setOnClickListener(v -> {
            raceId = spinner.getSelectedItem().toString();
            dbRace.document(raceId).delete().addOnCompleteListener(task -> {
                Toast.makeText(getContext(),"Your race has been deleted from Firebase",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(getContext(),"Your race has been not deleted from Firebase",Toast.LENGTH_SHORT).show();
            });

            list.remove(raceId);
            adapter.notifyDataSetChanged();

        });
        return view;
    }
}