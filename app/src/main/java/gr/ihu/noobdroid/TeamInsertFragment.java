package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import gr.ihu.noobdroid.LocalDB.LocalDB;

public class teamInsertFragment extends Fragment {

    public static LocalDB localDB;
    public TextInputEditText inputID;
    public TextInputEditText inputName;
    public TextInputEditText inputStadium;
    public TextInputEditText inputHeadquarters;
    public TextInputEditText inputCountry;
    public TextInputEditText inputEstablishYear;
    public Spinner spinnerSportID;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = TeamFragmentDirections
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_insert, container, false);
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

    private boolean isCountryValid() {
        // TODO Validate country
        return true;
    }

    private boolean isEstablishYearValid() {
        // TODO Validate establishYear
        return true;
    }
}