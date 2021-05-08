package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.muddzdev.styleabletoast.StyleableToast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;
import gr.ihu.noobdroid.LocalDB.Sportsman;
import gr.ihu.noobdroid.LocalDB.Team;

public class TeamAddPlayerFragment extends Fragment {

    public static LocalDB localDB;
    public int teamID;
    public int inTeamSelectedPlayer;
    public int notInTeamSelectedPlayer;
    public Spinner inTeamSpinner;
    public Spinner notInTeamSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = TeamAddPlayerFragmentArgs.fromBundle(getArguments()).getLocalDB();
        teamID = TeamAddPlayerFragmentArgs.fromBundle(getArguments()).getTeamID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_add_player, container, false);

        Button buttonInsert = view.findViewById(R.id.btn_player_insert);
        Button buttonDelete = view.findViewById(R.id.btn_player_delete);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team team = localDB.localDBInterface().getTeamByID(teamID);
                Sport sport = localDB.localDBInterface().getSportByID(team.getSportId());

                if (!sport.isTeamGame() && team.getTeamPlayersID().size() >= 1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("You can only add one player")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                    return;
                }

                if (notInTeamSelectedPlayer == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("No players to add")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    team.addPlayer(notInTeamSelectedPlayer);
                    localDB.localDBInterface().updateTeam(team);

                    refresh(v);
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inTeamSelectedPlayer == -1) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Team has no players")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GRAY)
                            .show();
                }
                else {
                    Team team = localDB.localDBInterface().getTeamByID(teamID);
                    team.removePlayer(inTeamSelectedPlayer);
                    localDB.localDBInterface().updateTeam(team);

                    refresh(v);
                }
            }
        });

        inTeamSpinner = view.findViewById(R.id.inTeamSpinner);
        notInTeamSpinner = view.findViewById(R.id.notInTeamSpinner);

        refresh(view);

        return view;
    }

    public void refresh(View view) {
        Team team = localDB.localDBInterface().getTeamByID(teamID);
        List<Sportsman> sportsman= localDB.localDBInterface().getSportsman();
        List<Integer> teamSportsmanIDs = team.getTeamPlayersID();

        ArrayList<String> sportsmanInTeam = new ArrayList<String>();
        ArrayList<String> sportsmanNotInTeam = new ArrayList<String>();
        ArrayList<Integer> sportsmanInTeamInt = new ArrayList<Integer>();
        ArrayList<Integer> sportsmanNotInTeamInt = new ArrayList<Integer>();

        inTeamSelectedPlayer = -1;
        notInTeamSelectedPlayer = -1;

        for (int i = 0; i < sportsman.size(); i++) {
            if (teamSportsmanIDs.contains(sportsman.get(i).getId())) {
                sportsmanInTeam.add(
                        "ID: " + sportsman.get(i).getId()
                                + ", Full Name: " + sportsman.get(i).getFirstName()
                                + " " + sportsman.get(i).getLastName()
                );
                sportsmanInTeamInt.add(sportsman.get(i).getId());
            }
            else {
                sportsmanNotInTeam.add(
                        "ID: " + sportsman.get(i).getId()
                                + ", Full Name: " + sportsman.get(i).getFirstName()
                                + " " + sportsman.get(i).getLastName()
                );
                sportsmanNotInTeamInt.add(sportsman.get(i).getId());
            }
        }

        ArrayAdapter adapterInTeam = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                sportsmanInTeam
        );
        adapterInTeam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter adapterNotInTeam = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                sportsmanNotInTeam
        );
        adapterNotInTeam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inTeamSpinner.setAdapter(adapterInTeam);
        notInTeamSpinner.setAdapter(adapterNotInTeam);

        inTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inTeamSelectedPlayer = sportsmanInTeamInt.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        notInTeamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                notInTeamSelectedPlayer = sportsmanNotInTeamInt.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}