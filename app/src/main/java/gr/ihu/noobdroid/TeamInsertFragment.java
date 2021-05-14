package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
import gr.ihu.noobdroid.LocalDB.Team;

public class TeamInsertFragment extends Fragment {

    public static LocalDB localDB;
    public TextInputEditText inputID;
    public TextInputEditText inputName;
    public TextInputEditText inputStadium;
    public TextInputEditText inputHeadquarters;
    public TextInputEditText inputCountry;
    public TextInputEditText inputEstablishYear;
    public TextInputEditText inputLocationX;
    public TextInputEditText inputLocationY;
    public Spinner spinnerSportID;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = TeamInsertFragmentArgs.fromBundle(getArguments()).getLocalDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_insert, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_team_modify);
        Button buttonCancel = view.findViewById(R.id.btn_team_cancel);
        inputID = view.findViewById(R.id.input_team_id);
        inputName = view.findViewById(R.id.input_team_name);
        inputStadium = view.findViewById(R.id.input_team_stadium);
        inputHeadquarters = view.findViewById(R.id.input_team_headquarters);
        inputCountry = view.findViewById(R.id.input_team_country);
        inputEstablishYear = view.findViewById(R.id.input_team_establishyear);
        inputLocationX = view.findViewById(R.id.input_team_locationx);
        inputLocationY = view.findViewById(R.id.input_team_locationy);
        spinnerSportID = view.findViewById(R.id.input_team_sport);

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

                if (! isNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isStadiumValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Stadium")
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

                if (! isLocationXValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid LocationX")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (! isLocationYValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid LocationY")
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

                if (! isEstablishYearValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Established Year")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                Team team = new Team(
                        Integer.parseInt(inputID.getText().toString()),
                        inputName.getText().toString(),
                        inputStadium.getText().toString(),
                        inputHeadquarters.getText().toString(),
                        Integer.parseInt(inputLocationX.getText().toString()),
                        Integer.parseInt(inputLocationX.getText().toString()),
                        inputCountry.getText().toString(),
                        selectedSportID,
                        Integer.parseInt(inputEstablishYear.getText().toString())
                );

                try {
                    localDB.localDBInterface().addTeam(team);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Team added")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "1")
                            .setSmallIcon(R.drawable.team)
                            .setContentTitle("Team")
                            .setContentText("Team added")
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("Team added"))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
                    notificationManager.notify(0, builder.build());
                } catch (Exception e) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Something went wrong")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
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

        spinnerSportID.setAdapter(adapter);
        spinnerSportID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private boolean isIdValid() {
        // TODO Validate id
        return true;
    }

    private boolean isNameValid() {
        // TODO Validate name
        return true;
    }

    private boolean isStadiumValid() {
        // TODO Validate stadium
        return true;
    }

    private boolean isHeadquartersValid() {
        // TODO Validate headquarters
        return true;
    }

    private boolean isLocationXValid() {
        /// TODO validate locationx
        return true;
    }

    private boolean isLocationYValid() {
        // TODO validate locationy
        return true;
    }

    private boolean isCountryValid() {
        // TODO Validate country
        return true;
    }

    private boolean isEstablishYearValid() {
        // TODO Validate establishYear
        return true;
    }

    private void reset() {
        inputID.setText("");
        inputName.setText("");
        inputStadium.setText("");
        inputHeadquarters.setText("");
        inputLocationX.setText("");
        inputLocationY.setText("");
        inputCountry.setText("");
        inputEstablishYear.setText("");
        spinnerSportID.setSelection(0);
    }

}