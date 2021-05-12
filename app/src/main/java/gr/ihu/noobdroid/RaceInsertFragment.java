package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import gr.ihu.noobdroid.firebase.Race;

public class RaceInsertFragment extends Fragment {

    public FirebaseFirestore db;

    public TextInputEditText inputID;
    public TextInputEditText inputDate;
    public TextInputEditText inputCity;
    public TextInputEditText inputCountry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_insert, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_modify_race);
        Button buttonCancel = view.findViewById(R.id.btn_cancel_race);
        inputID = view.findViewById(R.id.input_id);
        inputDate = view.findViewById(R.id.input_date);
        inputCity = view.findViewById(R.id.input_city);
        inputCountry = view.findViewById(R.id.input_country);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
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
               addDataToFirestore(Integer.parseInt(inputID.getText().toString()),
                       inputDate.getText().toString(),
                       inputCity.getText().toString(),
                       inputCountry.getText().toString());
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

    private void addDataToFirestore(int inputID,String inputDate,String inputCity,String inputCountry){

        CollectionReference dbRace = db.collection("Race");

        Race race = new Race(inputID,inputCity,inputCountry,inputDate);

        String id = String.valueOf(System.currentTimeMillis());

        dbRace.document(id).set(race).addOnSuccessListener(new OnSuccessListener<Void>() {
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
//                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getContext(),"Your race has been added to Firebase",Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @NotNull Exception e) {
//                Toast.makeText(getContext(),"Fail to add the Race\n" + e,Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private Boolean isIdValid() {
        // TODO Validate id
        return !inputID.getText().toString().equals("");
    }

    private Boolean isDateValid() {
        // TODO Validate date
        return !inputDate.getText().toString().equals("");
    }

    private Boolean isCityValid() {
        // TODO Validate city
        return !inputCity.getText().toString().equals("");
    }

    private Boolean isCountryValid() {
        // TODO Validate country
        return !inputCountry.getText().toString().equals("");
    }
}
