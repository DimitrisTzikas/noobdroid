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

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Sportsman;

public class SportsmanFragment extends Fragment {

    public static LocalDB localDB;
    public ArrayList<Integer> sportsmanIDs;
    public int selectedSportsmanID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportsmanFragmentArgs.fromBundle(getArguments()).getLocalDB();
        sportsmanIDs = new ArrayList<Integer>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportsman, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonModify = view.findViewById(R.id.btn_modify);
        Button buttonDelete = view.findViewById(R.id.btn_delete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SportsmanFragmentDirections.ActionSportsmanFragmentToSportsmanInsertFragment action;
                action = SportsmanFragmentDirections.actionSportsmanFragmentToSportsmanInsertFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSportsmanID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to modify")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    SportsmanFragmentDirections.ActionSportsmanFragmentToSportsmanModifyFragment action;
                    action = SportsmanFragmentDirections.actionSportsmanFragmentToSportsmanModifyFragment(localDB, selectedSportsmanID);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSportsmanID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to delete")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    localDB.localDBInterface().deleteSportsmanById(selectedSportsmanID);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sportsman deleted")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();
                }

                refresh(view);
            }
        });

        refresh(view);

        return view;
    }

    public void refresh(View view) {
        Spinner spinner = view.findViewById(R.id.output_spinner);

        List<Sportsman> sportsman = localDB.localDBInterface().getSportsman();
        ArrayList<String> sportsmanArray = new ArrayList<String>();
        sportsmanIDs = new ArrayList<Integer>();
        selectedSportsmanID = -1;

        for (int i = 0; i < sportsman.size(); i++) {
            sportsmanArray.add(
                    "ID: " + sportsman.get(i).getId()
                    + ", Full Name: " + sportsman.get(i).getFirstName()
                    + " " + sportsman.get(i).getLastName()
            );
            sportsmanIDs.add(sportsman.get(i).getId());
        }

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                sportsmanArray
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSportsmanID = sportsmanIDs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}