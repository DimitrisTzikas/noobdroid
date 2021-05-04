package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gr.ihu.noobdroid.LocalDB.LocalDB;

public class MainFragment extends Fragment {

    public static FragmentManager fragmentManager;
    public static LocalDB localDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = this.getParentFragmentManager();
        localDB = Room.databaseBuilder(
                this.getContext(),
                LocalDB.class,
                "local"
        ).allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.btn_sport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToSportFragment action;
                action = MainFragmentDirections.actionMainFragmentToSportFragment(localDB);
                Navigation.findNavController(v).navigate(action);

            }
        });

        view.findViewById(R.id.btn_sportsman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToSportsmanFragment action;
                action = MainFragmentDirections.actionMainFragmentToSportsmanFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        view.findViewById(R.id.btn_race).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.raceFragment);
            }
        });

        view.findViewById(R.id.btn_team).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentDirections.ActionMainFragmentToTeamFragment action;
                action = MainFragmentDirections.actionMainFragmentToTeamFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        return view;
    }
}