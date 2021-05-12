package gr.ihu.noobdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import gr.ihu.noobdroid.firebase.Race;

public class RaceRVAdapter extends RecyclerView.Adapter<RaceRVAdapter.ViewHolder> {

    private ArrayList<Race> raceArrayList;
    private Context context;

    public RaceRVAdapter(ArrayList<Race> raceArrayList, Context context){
        this.raceArrayList = raceArrayList;
        this.context = context;
    }

    @NotNull
    @Override
    public RaceRVAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_race_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RaceRVAdapter.ViewHolder holder, int position) {

        Race race = raceArrayList.get(position);
        holder.inputIDTV.setText(race.getId());
        holder.inputDateTV.setText(race.getDay());
        holder.inputCityTV.setText(race.getCity());
        holder.inputCountryTV.setText(race.getCountry());

    }

    @Override
    public int getItemCount() {
        return raceArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView inputIDTV;
        private final TextView inputDateTV;
        private final TextView inputCityTV;
        private final TextView inputCountryTV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            inputIDTV = itemView.findViewById(R.id.idTVinput_id);
            inputDateTV = itemView.findViewById(R.id.idTVinput_date);
            inputCityTV = itemView.findViewById(R.id.idTVinput_city);
            inputCountryTV = itemView.findViewById(R.id.idTVinput_country);

        }



    }
}
