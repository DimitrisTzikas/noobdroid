package gr.ihu.noobdroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Sportsman;
import gr.ihu.noobdroid.LocalDB.Team;

public class MainFragment extends Fragment {

    public LocalDB localDB;
    public String selectedDatabase;
    public String selectedField;
    public String selectedComparison;
    public String selectedValue;
    public int selectedValuePosition;
    public Spinner databaseSpinner;
    public Spinner fieldSpinner;
    public Spinner compareSpinner;
    public Spinner compareValueSpinner;
    public Spinner resultSpinner;
    public ArrayList<Integer> playersID;
    public ArrayList<Integer> sportsIDs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = Room.databaseBuilder(
                this.getContext(),
                LocalDB.class,
                "local"
        ).allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        databaseSpinner = view.findViewById(R.id.databaseSpinner);
        fieldSpinner = view.findViewById(R.id.fieldSpinner);
        compareSpinner = view.findViewById(R.id.compareSpinner);
        compareValueSpinner = view.findViewById(R.id.compareValueSpinner);
        resultSpinner = view.findViewById(R.id.resultSpinner);

        ArrayList<String> databases = new ArrayList<String>();
        databases.add("Sports");
        databases.add("Sportsman");
        databases.add("Teams");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                databases
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        databaseSpinner.setAdapter(adapter);
        databaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDatabase = databases.get(position);

                switch (selectedDatabase) {
                    case "Sports":
                        refreshFieldsSports(view);
                        break;
                    case "Sportsman":
                        refreshFieldsSportsman(view);
                        break;
                    case "Teams":
                        refreshFieldsTeams(view);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    public void refreshFieldsSports(View view) {
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("ID");
        fields.add("TeamGame");
        fields.add("MaleGame");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                fields
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fieldSpinner.setAdapter(adapter);
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedField = fields.get(position);

                switch (selectedField) {
                    case "ID":
                        refreshSelectedFieldInt(view);
                        return;
                    case "TeamGame":
                        refreshSelectedFieldBoolean(view);
                        return;
                    case "MaleGame":
                        refreshSelectedFieldBoolean(view);
                        return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshFieldsSportsman(View view) {
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("ID");
        fields.add("Sport");
        fields.add("Birth Year");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                fields
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fieldSpinner.setAdapter(adapter);
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedField = fields.get(position);

                switch (selectedField) {
                    case "ID":
                        refreshSelectedFieldInt(view);
                        return;
                    case "Sport":
                        refreshSelectedFieldSport(view);
                        return;
                    case "Birth Year":
                        refreshSelectedFieldInt(view);
                        return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshFieldsTeams(View view) {
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("ID");
        fields.add("Establish Year");
        fields.add("Player");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                fields
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fieldSpinner.setAdapter(adapter);
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedField = fields.get(position);

                switch (selectedField) {
                    case "ID":
                        refreshSelectedFieldInt(view);
                        return;
                    case "Establish Year":
                        refreshSelectedFieldInt(view);
                        return;
                    case "Player":
                        refreshSelectedFieldPlayer(view);
                        return;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshSelectedFieldInt(View view) {
        ArrayList<String> comparisons = new ArrayList<String>();
        comparisons.add("Equals");
        comparisons.add("Larger");
        comparisons.add("Smaller");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                comparisons
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compareSpinner.setAdapter(adapter);
        compareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedComparison = comparisons.get(position);
                refreshValue(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshSelectedFieldBoolean(View view) {
        ArrayList<String> comparisons = new ArrayList<String>();
        comparisons.add("Equals");
        comparisons.add("Unequal");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                comparisons
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compareSpinner.setAdapter(adapter);
        compareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedComparison = comparisons.get(position);
                refreshValue(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshSelectedFieldSport(View view) {
        ArrayList<String> comparisons = new ArrayList<String>();
        comparisons.add("Has");
        comparisons.add("Hasn't");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                comparisons
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compareSpinner.setAdapter(adapter);
        compareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedComparison = comparisons.get(position);
                refreshValue(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshSelectedFieldPlayer(View view) {
        ArrayList<String> comparisons = new ArrayList<String>();
        comparisons.add("Has");
        comparisons.add("Hasn't");

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                comparisons
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compareSpinner.setAdapter(adapter);
        compareSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedComparison = comparisons.get(position);
                refreshValue(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void refreshValue(View view) {
        ArrayList<String> array = new ArrayList<String>();

        switch (selectedDatabase) {
            case "Sports":
                switch (selectedField) {
                    case "ID":
                        for (int i = 0; i <= 20; i++)
                            array.add(String.valueOf(i));
                        break;
                    case "TeamGame":
                    case "MaleGame":
                        array.add("True");
                        array.add("False");
                        break;
                }
                break;
            case "Sportsman":
                switch (selectedField) {
                    case "ID":
                        for (int i = 0; i <= 20; i++)
                            array.add(String.valueOf(i));
                        break;
                    case "Sport":
                        List<Sport> sports = localDB.localDBInterface().getSports();
                        sportsIDs = new ArrayList<Integer>();

                        for (int i = 0; i < sports.size(); i++) {
                            sportsIDs.add(sports.get(i).getId());
                            array.add(
                                    "ID: " + sports.get(i).getId()
                                            + ", Name: " + sports.get(i).getName()
                            );
                        }
                        break;
                    case "Birth Year":
                        for (int i = 1960; i <= 2010; i++)
                            array.add(String.valueOf(i));
                        break;
                }
                break;
            case "Teams":
                switch (selectedField) {
                    case "ID":
                        for (int i = 0; i <= 20; i++)
                            array.add(String.valueOf(i));
                        break;
                    case "Establish Year":
                        for (int i = 1960; i <= 2010; i++)
                            array.add(String.valueOf(i));
                        break;
                    case "Player":
                        List<Sportsman> sportsman = localDB.localDBInterface().getSportsman();
                        playersID = new ArrayList<Integer>();

                        for (int i = 0; i < sportsman.size(); i++) {
                            playersID.add(sportsman.get(i).getId());
                            array.add(
                                    "ID: " + sportsman.get(i).getId()
                                            + ", Full Name: " + sportsman.get(i).getFirstName()
                                            + " " + sportsman.get(i).getLastName()
                            );
                        }
                        break;
                }
                break;
        }

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                array
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        compareValueSpinner.setAdapter(adapter);
        compareValueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedValue = array.get(position);
                selectedValuePosition = position;
                executeQuery(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void executeQuery(View view) {
        ArrayList<String> output = new ArrayList<String>();

        switch (selectedDatabase) {
            case "Sports":
                List<Sport> sports = localDB.localDBInterface().getSports();

                switch (selectedField) {
                    case "ID":
                        switch (selectedComparison) {
                            case "Equals":
                                for (int i = 0; i < sports.size(); i++) {
                                    if (sports.get(i).getId() == Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sports.get(i).getId()
                                                        + ", Name: " + sports.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Larger":
                                for (int i = 0; i < sports.size(); i++) {
                                    if (sports.get(i).getId() > Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sports.get(i).getId()
                                                        + ", Name: " + sports.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Smaller":
                                for (int i = 0; i < sports.size(); i++) {
                                    if (sports.get(i).getId() < Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sports.get(i).getId()
                                                        + ", Name: " + sports.get(i).getName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                    case "TeamGame":
                        if (selectedComparison.equals("Equals")) {
                            for (int i = 0; i < sports.size(); i++) {
                                if (sports.get(i).isTeamGame() == selectedValue.equals("True")) {
                                    output.add(
                                            "ID: " + sports.get(i).getId()
                                                    + ", Name: " + sports.get(i).getName()
                                    );
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < sports.size(); i++) {
                                if (sports.get(i).isTeamGame() != selectedValue.equals("True")) {
                                    output.add(
                                            "ID: " + sports.get(i).getId()
                                                    + ", Name: " + sports.get(i).getName()
                                    );
                                }
                            }
                        }
                        break;
                    case "MaleGame":
                        if (selectedComparison.equals("Equals")) {
                            for (int i = 0; i < sports.size(); i++) {
                                if (sports.get(i).isMaleGame() == selectedValue.equals("True")) {
                                    output.add(
                                            "ID: " + sports.get(i).getId()
                                                    + ", Name: " + sports.get(i).getName()
                                    );
                                }
                            }
                        }
                        else {
                            for (int i = 0; i < sports.size(); i++) {
                                if (sports.get(i).isMaleGame() != selectedValue.equals("True")) {
                                    output.add(
                                            "ID: " + sports.get(i).getId()
                                                    + ", Name: " + sports.get(i).getName()
                                    );
                                }
                            }
                        }
                        break;
                }
                break;
            case "Sportsman":
                List<Sportsman> sportsman = localDB.localDBInterface().getSportsman();

                switch (selectedField) {
                    case "ID":
                        switch (selectedComparison) {
                            case "Equals":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getId() == Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Full Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                            case "Larger":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getId() > Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                            case "Smaller":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getId() < Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                    case "Sport":
                        switch (selectedComparison) {
                            case "Has":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getSportId() == sportsIDs.get(selectedValuePosition)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                            case "Hasn't":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getSportId() != sportsIDs.get(selectedValuePosition)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                    case "Birth Year":
                        switch (selectedComparison) {
                            case "Equals":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getBirthYear() == Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Full Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                            case "Larger":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getBirthYear() > Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Full Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                            case "Smaller":
                                for (int i = 0; i < sportsman.size(); i++) {
                                    if (sportsman.get(i).getBirthYear() < Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + sportsman.get(i).getId()
                                                        + ", Full Name: " + sportsman.get(i).getFirstName()
                                                        + " " + sportsman.get(i).getLastName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                }
                break;
            case "Teams":
                List<Team> teams = localDB.localDBInterface().getTeams();

                switch (selectedField) {
                    case "ID":
                        switch (selectedComparison) {
                            case "Equals":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getId() == Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Larger":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getId() > Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Smaller":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getId() < Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                    case "Establish Year":
                        switch (selectedComparison) {
                            case "Equals":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getEstablishYear() == Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Larger":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getEstablishYear() > Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Full Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Smaller":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).getEstablishYear() < Integer.parseInt(selectedValue)) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Full Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                    case "Player":
                        switch (selectedComparison) {
                            case "Has":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (teams.get(i).hasPlayer(
                                            playersID.get(
                                                    selectedValuePosition
                                            )
                                    )) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                            case "Hasn't":
                                for (int i = 0; i < teams.size(); i++) {
                                    if (!teams.get(i).hasPlayer(
                                            playersID.get(
                                                    selectedValuePosition
                                            )
                                    )) {
                                        output.add(
                                                "ID: " + teams.get(i).getId()
                                                        + ", Name: " + teams.get(i).getName()
                                        );
                                    }
                                }
                                break;
                        }
                        break;
                }
                break;
        }

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                output
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        resultSpinner.setAdapter(adapter);

    }

}