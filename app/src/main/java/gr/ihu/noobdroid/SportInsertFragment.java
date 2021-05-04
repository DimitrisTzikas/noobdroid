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

import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;

public class SportInsertFragment extends Fragment {

    public static LocalDB localDB;
    public TextInputEditText inputID;
    public TextInputEditText inputName;
    public CheckBox boxTeamGame;
    public CheckBox boxMaleGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportInsertFragmentArgs.fromBundle(getArguments()).getLocalDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_insert, container, false);

        inputID = view.findViewById(R.id.input_id);
        inputName = view.findViewById(R.id.input_name);
        boxTeamGame = view.findViewById(R.id.box_team_game);
        boxMaleGame = view.findViewById(R.id.box_male_game);
        Button buttonInsert = view.findViewById(R.id.btn_insert);
        Button buttonCancel = view.findViewById(R.id.btn_cancel);

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

                Sport sport = new Sport(
                        Integer.parseInt(inputID.getText().toString()),
                        inputName.getText().toString(),
                        boxTeamGame.isChecked(),
                        boxMaleGame.isChecked()
                );
                try {
                    localDB.localDBInterface().addSport(sport);
                    new StyleableToast
                            .Builder(getContext())
                            .text("Sport added")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.GREEN)
                            .show();
                } catch (Exception e) {
                    new StyleableToast
                            .Builder(getContext())
                            .text("Something went wrong")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
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

        return view;
    }

    private Boolean isIdValid() {
        // TODO
        // Validate id
        return !inputID.getText().toString().equals("");
    }

    private Boolean isNameValid() {
        // TODO
        // Validate name
        return !inputName.getText().toString().equals("");
    }

    private void reset() {
        inputID.setText("");
        inputName.setText("");
        boxTeamGame.setChecked(false);
        boxMaleGame.setChecked(false);
    }

}