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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Sportsman;

public class SportsmanModifyFragment extends Fragment {

    public static LocalDB localDB;
    public int sportsmanID;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;
    public int sportID;
    public TextInputEditText inputID;
    public TextInputEditText inputFirstName;
    public TextInputEditText inputLastName;
    public TextInputEditText inputHeadquarters;
    public TextInputEditText inputCountry;
    public TextInputEditText inputBirthYear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportsmanModifyFragmentArgs.fromBundle(getArguments()).getLocalDB();
        sportsmanID = SportsmanModifyFragmentArgs.fromBundle(getArguments()).getSportsmanID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sportsman_modify, container, false);

        Sportsman sportsman = localDB.localDBInterface().getSportsmanByID(sportsmanID);
        Button buttonModify = view.findViewById(R.id.btn_modify_sportsman);
        Button buttonCancel = view.findViewById(R.id.btn_cancel_sportsman);
        Spinner spinnerSport = view.findViewById(R.id.input_sport);
        sportID = sportsman.getSportId();
        inputID = view.findViewById(R.id.input_id);
        inputFirstName = view.findViewById(R.id.input_first_name);
        inputLastName = view.findViewById(R.id.input_last_name);
        inputHeadquarters = view.findViewById(R.id.input_headquarters);
        inputCountry = view.findViewById(R.id.input_country);
        inputBirthYear = view.findViewById(R.id.input_birth_year);

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
            if (sports.get(i).getId() == sportID) {
                selectedSportID = sportID;
            }
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
                if (position > 0) {
                    selectedSportID = sportsIDs.get(position-1);
                }
                else {
                    selectedSportID = -1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean modified = false;

                if (!isFirstNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid First Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!isLastNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Last Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!isHeadquartersValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Headquarters")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!isCountryValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Country")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!isBirthYearValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Birth Year")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!inputFirstName.getText().toString().equals(sportsman.getFirstName())) {
                    sportsman.setFirstName(inputFirstName.getText().toString());
                    modified = true;
                }

                if (!inputLastName.getText().toString().equals(sportsman.getLastName())) {
                    sportsman.setLastName(inputLastName.getText().toString());
                    modified = true;
                }

                if (!inputHeadquarters.getText().toString().equals(sportsman.getHeadquarters())) {
                    sportsman.setHeadquarters(inputHeadquarters.getText().toString());
                    modified = true;
                }

                if (!inputCountry.getText().toString().equals(sportsman.getCountry())) {
                    sportsman.setCountry(inputCountry.getText().toString());
                    modified = true;
                }

                if (Integer.parseInt(inputBirthYear.getText().toString()) != sportsman.getBirthYear()) {
                    sportsman.setBirthYear(Integer.parseInt(inputBirthYear.getText().toString()));
                    modified = true;
                }

                if (selectedSportID != sportID) {
                    sportsman.setSportId(selectedSportID);
                    sportID = selectedSportID;
                    modified = true;
                }

                if (modified) {
                    localDB.localDBInterface().updateSportsman(sportsman);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sportman modified")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();

                    Navigation.findNavController(view).popBackStack();
                }
                else {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to modify")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        inputID.setText(String.valueOf(sportsman.getId()));
        inputFirstName.setText(sportsman.getFirstName());
        inputLastName.setText(sportsman.getLastName());
        inputHeadquarters.setText(sportsman.getHeadquarters());
        inputCountry.setText(sportsman.getCountry());
        inputBirthYear.setText(String.valueOf(sportsman.getBirthYear()));
        spinnerSport.setSelection(selectedSportID);

        return view;
    }

    public boolean isFirstNameValid() {
        // TODO validate firstname
        return true;
    }

    public boolean isLastNameValid() {
        // TODO validate lastname
        return true;
    }

    public boolean isHeadquartersValid() {
        // TODO validate headquarters
        return true;
    }

    public boolean isCountryValid() {
        // TODO validate country
        return true;
    }

    public boolean isBirthYearValid() {
        // TODO validate birthyear
        return true;
    }

}