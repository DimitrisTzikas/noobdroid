package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import gr.ihu.noobdroid.LocalDB.LocalDB;
import gr.ihu.noobdroid.LocalDB.Sport;

public class SportInsertFragment extends Fragment {

    private static LocalDB localDB;
    private TextInputEditText inputID;
    private TextInputEditText inputName;
    private CheckBox boxTeamGame;
    private CheckBox boxMaleGame;
    private Button buttonInsert;
    private Button buttonCancel;

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
        buttonInsert = view.findViewById(R.id.btn_insert);
        buttonCancel = view.findViewById(R.id.btn_cancel);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! isIdValid()) {
                    // Handle error
                    return;
                }

                if (! isNameValid()) {
                    // Handle error
                    return;
                }

                Sport sport = new Sport(
                        Integer.parseInt(inputID.getText().toString()),
                        inputName.toString(),
                        boxTeamGame.isChecked(),
                        boxMaleGame.isChecked()
                );
                try {
                    localDB.localDBInterface().addSport(sport);
                    Toast.makeText(getContext(), "Sport added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
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
        // Validate id
        return true;
    }

    private Boolean isNameValid() {
        // Validate name
        return true;
    }

    private void reset() {
        inputID.setText("");
        inputName.setText("");
        boxTeamGame.setChecked(false);
        boxMaleGame.setChecked(false);
    }

}