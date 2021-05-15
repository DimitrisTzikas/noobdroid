package gr.ihu.noobdroid;

import android.content.Intent;
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

        editDate = view.findViewById(R.id.edit_date);
        editCity = view.findViewById(R.id.edit_city);
        editCountry = view.findViewById(R.id.edit_country);

        dbRace.document(raceId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String city = documentSnapshot.getString("city");
                    String country = documentSnapshot.getString("country");
                    String date = documentSnapshot.getString("day");

                    editCity.setText(city);
                    editCountry.setText(country);
                    editDate.setText(date);

                } else {
                    // Message Error
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                // Message Error
            }
        });

        //firebase load race data

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                editDataToFirestore(editDate.getText().toString(),
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

    private void editDataToFirestore(String edit_Date,String edit_City,String edit_Country){

        Race race = new Race(edit_City,edit_Country,edit_Date);

        dbRace.document(raceId).set(race).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //toast
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                //toast
            }
        });
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