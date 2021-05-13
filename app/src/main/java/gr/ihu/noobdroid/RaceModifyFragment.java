package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.firebase.Race;

public class RaceModifyFragment extends Fragment {

    public FirebaseFirestore db;
    public CollectionReference dbRace;
    public TextInputEditText editID;
    public TextInputEditText editDate;
    public TextInputEditText editCity;
    public TextInputEditText editCountry;
    public String raceId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        dbRace = db.collection("Race");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_modify, container, false);

        raceId = RaceModifyFragmentArgs.fromBundle(requireArguments()).getRaceId();
        Button buttonModify = view.findViewById(R.id.btn_modify_race);
        Button buttonCancel = view.findViewById(R.id.btn_cancel_race);

        editID = view.findViewById(R.id.edit_id);
        editDate = view.findViewById(R.id.edit_date);
        editCity = view.findViewById(R.id.edit_city);
        editCountry = view.findViewById(R.id.edit_country);

        //firebase load race data

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isIdValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid ID")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }
                if (!isDateValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Date")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }
                if (!isCityValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid City")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }
                if (!isCountryValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Headquarters")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }
                editDataToFirestore(Integer.parseInt(editID.getText().toString()),
                        editDate.getText().toString(),
                        editCity.getText().toString(),
                        editCountry.getText().toString());
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        return view;
    }

    private void editDataToFirestore(int editID,String editDate,String editCity,String editCountry){

        Race race = new Race(editID,editCity,editCountry,editDate);

        dbRace.document(raceId).set(race).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(),"Your race has been added to Firebase",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getContext(),"Fail to add the Race\n" + e,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Boolean isIdValid() {
        // TODO Validate id
        return !editID.getText().toString().equals("");
    }

    private Boolean isDateValid() {
        // TODO Validate date
        return !editDate.getText().toString().equals("");
    }

    private Boolean isCityValid() {
        // TODO Validate city
        return !editCity.getText().toString().equals("");
    }

    private Boolean isCountryValid() {
        // TODO Validate country
        return !editCountry.getText().toString().equals("");
    }
}