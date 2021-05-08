package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Sportsman;

public class SportsmanInsertFragment extends Fragment {

    public static LocalDB localDB;
    public TextInputEditText inputID;
    public TextInputEditText inputFirstName;
    public TextInputEditText inputLastName;
    public TextInputEditText inputHeadquarters;
    public TextInputEditText inputCountry;
    public TextInputEditText inputBirthYear;
    public Spinner spinnerSport;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportsmanInsertFragmentArgs.fromBundle(getArguments()).getLocalDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportsman_insert, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_modify_sportsman);
        Button buttonCancel = view.findViewById(R.id.btn_cancel_sportsman);
        inputID = view.findViewById(R.id.input_id);
        inputFirstName = view.findViewById(R.id.input_first_name);
        inputLastName = view.findViewById(R.id.input_last_name);
        inputHeadquarters = view.findViewById(R.id.input_headquarters);
        inputCountry = view.findViewById(R.id.input_country);
        inputBirthYear = view.findViewById(R.id.input_birth_year);
        spinnerSport = view.findViewById(R.id.input_sport);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! isIdValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid ID")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isFirstNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid First Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isLastNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Last Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isHeadquartersValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Headquarters")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isCountryValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Country")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isBirthYearValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Birth Year")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                Sportsman sportsman = new Sportsman(
                        Integer.parseInt(inputID.getText().toString()),
                        inputFirstName.getText().toString(),
                        inputLastName.getText().toString(),
                        inputHeadquarters.getText().toString(),
                        inputCountry.getText().toString(),
                        selectedSportID,
                        Integer.parseInt(inputBirthYear.getText().toString())
                );

                try {
                    localDB.localDBInterface().addSportsman(sportsman);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sportsman added")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();
                } catch (Exception e) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Something went wrong")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                }

                reset();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        List<Sport> sports = localDB.localDBInterface().getSports();
        ArrayList<String> sportsArray = new ArrayList<String>();
        sportsIDs = new ArrayList<Integer>();
        selectedSportID = -1;

        sportsArray.add("None");
        for (int i = 0; i < sports.size(); i++) {
            sportsArray.add(
                    "ID: " + sports.get(i).getId()
                            + ", Name: " + sports.get(i).getName()
            );
            sportsIDs.add(sports.get(i).getId());
        }

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                sportsArray
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSport.setAdapter(adapter);
        spinnerSport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    selectedSportID = -1;
                }
                else {
                    selectedSportID = sportsIDs.get(position-1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    private Boolean isIdValid() {
        // TODO Validate id
        return !inputID.getText().toString().equals("");
    }

    private Boolean isFirstNameValid() {
        // TODO Validate firstName
        return !inputFirstName.getText().toString().equals("");
    }

    private Boolean isLastNameValid() {
        // TODO Validate lastName
        return !inputLastName.getText().toString().equals("");
    }

    private Boolean isHeadquartersValid() {
        // TODO Validate headquarters
        return !inputHeadquarters.getText().toString().equals("");
    }

    private Boolean isCountryValid() {
        // TODO Validate country
        return !inputCountry.getText().toString().equals("");
    }

    private Boolean isBirthYearValid() {
        // TODO Validate brithyear
        return !inputBirthYear.getText().toString().equals("");
    }

    private void reset() {
        inputID.setText("");
        inputFirstName.setText("");
        inputLastName.setText("");
        inputHeadquarters.setText("");
        inputCountry.setText("");
        inputBirthYear.setText("");
        spinnerSport.setSelection(0);
    }

}