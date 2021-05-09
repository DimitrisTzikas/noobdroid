package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    public String selectedDatabase;
    public String selectedField;
    public String selectedComparison;
    public String selectedValue;
    public Spinner databaseSpinner;
    public Spinner fieldSpinner;
    public Spinner compareSpinner;
    public Spinner compareValueSpinner;
    public Spinner resultSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        comparisons.add("Is");
        comparisons.add("Isn't");

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
        comparisons.add("Have");
        comparisons.add("Haven't");

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
                        // TODO
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
                        // TODO
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}