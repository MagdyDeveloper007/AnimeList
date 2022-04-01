package developer007.magdy.animelist.data;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import developer007.magdy.animelist.R;
import developer007.magdy.animelist.ui.home.AnimeListModule;


public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder> {

    private AnimeListModule list = new AnimeListModule();

    private String strTitle, strSynopsis, strType, strRated, srtImage;
    private String strEpisodes, strScore;


    public static AppCompatActivity appCompatActivity;

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_animelist, parent, false);

        appCompatActivity = (AppCompatActivity) view.getContext();
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        strTitle = list.getResults().get(position).getTitle();

        strSynopsis = "Synopsis: " + list.getResults().get(position).getSynopsis();

        strType = "Type: " + list.getResults().get(position).getType();

        strEpisodes = "Episodes: " + list.getResults().get(position).getEpisodes();

        strScore = "Score: " + list.getResults().get(position).getScore();

        strRated = "Rated: " + list.getResults().get(position).getRated();
        srtImage = list.getResults().get(position).getImage_url();

        String RxRate = "Rated: Rx";
        if (!strRated.equals(RxRate)) {
            if (TextUtils.equals(strRated, "Rated: " + null)) {
                holder.tvrRated.setText("N/A");

            } else {
                holder.tvrRated.setText(strRated);
            }
            holder.tvAnimeTitle.setText(strTitle);
            holder.tvSynopsis.setText(strSynopsis);
            holder.tvType.setText(strType);
            holder.tvEpisodes.setText(strEpisodes);
            holder.tvScore.setText(strScore);
            handlingImage(srtImage, holder);

        }


    }

    @Override
    public int getItemCount() {
        if (list.getResults() != null) {
            return list.getResults().size();
        } else {
            return 0;
        }
    }

    public void setList(AnimeListModule list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        TextView tvAnimeTitle, tvSynopsis, tvType, tvEpisodes, tvScore, tvrRated;
        ImageView imgUrl;


        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAnimeTitle = itemView.findViewById(R.id.tvAnimeTitle);
            tvSynopsis = itemView.findViewById(R.id.tvSynopsis);
            tvType = itemView.findViewById(R.id.tvType);
            tvEpisodes = itemView.findViewById(R.id.tvEpisodes);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvrRated = itemView.findViewById(R.id.tvrRated);
            imgUrl = itemView.findViewById(R.id.imgUrl);

        }
    }

    public void handlingImage(String image, AnimeViewHolder holder) {
        Picasso.get().load(image).into(holder.imgUrl);

    }
}