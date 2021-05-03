package gr.ihu.noobdroid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;

public class SportModifyFragment extends Fragment {

    public static LocalDB localDB;
    public int sportID;
    public Sport sport;
    public TextView outputID;
    public TextInputEditText inputName;
    public CheckBox boxTeamGame;
    public CheckBox boxMaleGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportModifyFragmentArgs.fromBundle(getArguments()).getLocalDB();
        sportID = SportModifyFragmentArgs.fromBundle(getArguments()).getSportID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_modify, container, false);

        sport = localDB.localDBInterface().getSportByID(sportID);
        Button buttonModify = view.findViewById(R.id.btn_confirm);
        Button buttonCancel = view.findViewById(R.id.btn_cancel);

        outputID = view.findViewById(R.id.output_id);
        inputName = view.findViewById(R.id.input_name);
        boxTeamGame = view.findViewById(R.id.box_team_game);
        boxMaleGame = view.findViewById(R.id.box_male_game);

        inputName.setText(sport.getName());
        boxTeamGame.setChecked(sport.isTeamGame());
        boxMaleGame.setChecked(sport.isMaleGame());

        outputID.setText(
                String.format("ID: %s", String.valueOf(sportID))
        );

        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean modified = false;

                String name = inputName.getText().toString();

                if (!isNameValid()) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Invalid Name")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    return;
                }

                if (!name.equals(sport.getName())) {
                    sport.setName(name);
                    modified = true;
                }

                if (boxTeamGame.isChecked() != sport.isTeamGame()) {
                    sport.setTeamGame(boxTeamGame.isChecked());
                    modified = true;
                }

                if (boxMaleGame.isChecked() != sport.isMaleGame()) {
                    sport.setMaleGame(boxMaleGame.isChecked());
                    modified = true;
                }

                if (modified) {
                    localDB.localDBInterface().updateSport(sport);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sport modified")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();

                    Navigation.findNavController(v).popBackStack();
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

        return view;
    }

    public boolean isNameValid() {
        // validate name
        return true;
    }

}