package gr.ihu.noobdroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gr.ihu.noobdroid.LocalDB.LocalDB;

public class SportsmanInsertFragment extends Fragment {

    public static LocalDB localDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sportsman_insert, container, false);
        return view;
    }
}