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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Team;

public class TeamModifyFragment extends Fragment {

    public static LocalDB localDB;
    public int teamID;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;
    public int sportID;
    public TextView inputID;
    public TextInputEditText inputName;
    public TextInputEditText inputStadium;
    public TextInputEditText inputHeadquarters;
    public TextInputEditText inputCountry;
    public TextInputEditText inputEstablishYear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = TeamModifyFragmentArgs.fromBundle(getArguments()).getLocalDB();
        teamID = TeamModifyFragmentArgs.fromBundle(getArguments()).getTeamID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_modify, container, false);

        Team team = localDB.localDBInterface().getTeamByID(teamID);
        Button buttonModify = view.findViewById(R.id.btn_team_modify);
        Button buttonCancel = view.findViewById(R.id.btn_team_cancel);
        Spinner spinnerSport = view.findViewById(R.id.input_team_sport);
        sportID = team.getSportId();
        inputID = view.findViewById(R.id.output_team_id);
        inputName = view.findViewById(R.id.input_team_name);
        inputStadium = view.findViewById(R.id.input_team_stadium);
        inputHeadquarters = view.findViewById(R.id.input_team_headquarters);
        inputCountry = view.findViewById(R.id.input_team_country);
        inputEstablishYear = view.findViewById(R.id.input_team_establishyear);

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

                if (!isNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid First Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!isStadiumValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Stadium")
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

                if (!isEstablishYearValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Establish Year")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!inputName.getText().toString().equals(team.getName())) {
                    team.setName(inputName.getText().toString());
                    modified = true;
                }

                if (!inputStadium.getText().toString().equals(team.getStadium())) {
                    team.setStadium(inputStadium.getText().toString());
                    modified = true;
                }

                if (!inputHeadquarters.getText().toString().equals(team.getHeadquarters())) {
                    team.setHeadquarters(inputHeadquarters.getText().toString());
                    modified = true;
                }

                if (!inputCountry.getText().toString().equals(team.getCountry())) {
                    team.setCountry(inputCountry.getText().toString());
                    modified = true;
                }

                if (team.getEstablishYear() != Integer.valueOf(inputEstablishYear.getText().toString())) {
                    team.setEstablishYear(Integer.valueOf(inputEstablishYear.getText().toString()));
                    modified = true;
                }

                if (selectedSportID != sportID) {
                    team.setSportId(selectedSportID);
                    sportID = selectedSportID;
                    modified = true;
                }

                if (modified) {
                    localDB.localDBInterface().updateTeam(team);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Team modified")
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

        inputID.setText(String.valueOf(team.getSportId()));
        inputName.setText(team.getName());
        inputStadium.setText(team.getStadium());
        inputHeadquarters.setText(team.getHeadquarters());
        inputCountry.setText(team.getCountry());
        inputEstablishYear.setText(String.valueOf(team.getEstablishYear()));
        spinnerSport.setSelection(selectedSportID);

        return view;
    }

    private boolean isNameValid() {
        // TODO validate name
        return true;
    }

    private boolean isStadiumValid() {
        // TODO validate headquarters
        return true;
    }

    private boolean isCountryValid() {
        // TODO validate country
        return true;
    }

    private boolean isEstablishYearValid() {
        // TODO validate establishyear
        return true;
    }

}