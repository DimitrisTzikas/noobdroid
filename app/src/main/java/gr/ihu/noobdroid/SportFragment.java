package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gr.ihu.noobdroid.LocalDB.LocalDB;

public class SportFragment extends Fragment {

    public static LocalDB localDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDB = SportFragmentArgs.fromBundle(getArguments()).getLocalDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);

        view.findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SportFragmentDirections.ActionSportFragmentToSportInsertFragment action;
                action = SportFragmentDirections.actionSportFragmentToSportInsertFragment(localDB);
                Navigation.findNavController(v).navigate(action);
            }
        });

        return view;
    }
}