package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.muddzdev.styleabletoast.StyleableToast;

import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Team;

public class TeamFragment extends Fragment {

    public static LocalDB localDB;
    public ArrayList<Integer> teamIDs;
    public int selectedTeamID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = Room.databaseBuilder(
                this.getContext(),
                LocalDB.class,
                "local"
        ).allowMainThreadQueries().build();
        teamIDs = new ArrayList<Integer>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonModify = view.findViewById(R.id.btn_modify);
        Button buttonDelete = view.findViewById(R.id.btn_delete);
        Button buttonAddPlayers = view.findViewById(R.id.btn_add_players);
        Button buttonMap = view.findViewById(R.id.btn_map);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TeamFragmentDirections.ActionTeamFragmentToTeamInsertFragment action;
                action = TeamFragmentDirections.actionTeamFragmentToTeamInsertFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTeamID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to modify")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    TeamFragmentDirections.ActionTeamFragmentToTeamModifyFragment action;
                    action = TeamFragmentDirections.actionTeamFragmentToTeamModifyFragment(localDB, selectedTeamID);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTeamID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to delete")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    localDB.localDBInterface().deleteTeamByID(selectedTeamID);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Team deleted")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();
                }

                refresh(view);
            }
        });

        buttonAddPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTeamID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("No team to add players")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    TeamFragmentDirections.ActionTeamFragmentToTeamAddPlayerFragment action;
                    action = TeamFragmentDirections.actionTeamFragmentToTeamAddPlayerFragment(localDB, selectedTeamID);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTeamID == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Nothing to show")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    Team team = null;
                    List<Team> teams = localDB.localDBInterface().getTeams();
                    for (int i = 0; i < teams.size(); i++) {
                        if (teams.get(i).getId() == selectedTeamID) {
                            team = teams.get(i);
                            break;
                        }
                    }

                    if (team != null) {
                        // TODO GET LOCATION OF HQ
                        TeamFragmentDirections.ActionTeamFragmentToMapsFragment action;
                        action = TeamFragmentDirections.actionTeamFragmentToMapsFragment(
                                team.getLocationX(),
                                team.getLocationY(),
                                team.getHeadquarters()
                        );
                        Navigation.findNavController(v).navigate(action);
                    }
                }
            }
        });

        refresh(view);

        return view;
    }

    public void refresh(View view) {
        Spinner spinner = view.findViewById(R.id.output_spinner);

        List<Team> teams = localDB.localDBInterface().getTeams();
        ArrayList<String> teamArray = new ArrayList<String>();
        teamIDs = new ArrayList<Integer>();
        selectedTeamID = -1;

        for (int i = 0; i < teams.size(); i++) {
            teamArray.add(
                    "ID: " + teams.get(i).getId()
                    + ", Name: " + teams.get(i).getName()
            );
            teamIDs.add(teams.get(i).getId());
        }

        ArrayAdapter adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                teamArray
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTeamID = teamIDs.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}