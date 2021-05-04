package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;

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
        Button buttonDelete = view.findViewById(R.id.btn_modify);

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
                SportsmanFragmentDirections.ActionSportsmanFragmentToSportsmanModifyFragment action;
                action = SportsmanFragmentDirections.actionSportsmanFragmentToSportsmanModifyFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        refresh(view);

        return view;
    }

    public void refresh(View view) {
        // TODO
    }

}