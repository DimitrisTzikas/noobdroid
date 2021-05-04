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

public class SportFragment extends Fragment {

    public static LocalDB localDB;
    public ArrayList<Integer> sportsIDs;
    public int selectedSportID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportFragmentArgs.fromBundle(getArguments()).getLocalDB();
        sportsIDs = new ArrayList<Integer>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonModify = view.findViewById(R.id.btn_modify);
        Button buttonDelete = view.findViewById(R.id.btn_delete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SportFragmentDirections.ActionSportFragmentToSportInsertFragment action;
                action = SportFragmentDirections.actionSportFragmentToSportInsertFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSportID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to modify")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    SportFragmentDirections.ActionSportFragmentToSportModifyFragment action;
                    action = SportFragmentDirections.actionSportFragmentToSportModifyFragment(localDB, selectedSportID);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSportID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to delete")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    localDB.localDBInterface().deleteSportByID(selectedSportID);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sport deleted")
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

        List<Sport> sports = localDB.localDBInterface().getSports();
        ArrayList<String> sportsArray = new ArrayList<String>();
        sportsIDs = new ArrayList<Integer>();
        selectedSportID = -1;

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

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSportID = sportsIDs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}