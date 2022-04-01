package developer007.magdy.animelist.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import developer007.magdy.animelist.R;
import developer007.magdy.animelist.data.SharedPrefManager;
import developer007.magdy.animelist.data.AnimeListAdapter;

public class AnimeListFragment extends Fragment {

    private AnimeListViewModel animeListViewModel;
    private EditText etAnimeTitle;
    private ImageButton imgSearch;
    private ProgressBar progress1;
    private String strAnimeTitle;
    private SharedPrefManager sharedPrefManager;
    private RecyclerView recyclerUniversities;
    private AnimeListAdapter animeListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_animelist, container, false);
        AppCompatActivity compatActivity = (AppCompatActivity) root.getContext();

        animeListViewModel =
                new ViewModelProvider(this).get(AnimeListViewModel.class);


        sharedPrefManager = new SharedPrefManager();

        etAnimeTitle = root.findViewById(R.id.etAnimeTitle);
        imgSearch = root.findViewById(R.id.imgSearch);
        progress1 = root.findViewById(R.id.progress1);
        recyclerUniversities = root.findViewById(R.id.recyclerAnime);


        strAnimeTitle = sharedPrefManager.getAuthPref(compatActivity).getString("name", "");

        if (!TextUtils.isEmpty(strAnimeTitle)) {
            etAnimeTitle.setText(strAnimeTitle);
        }


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(compatActivity);

        recyclerUniversities.setNestedScrollingEnabled(true);
        animeListAdapter = new AnimeListAdapter();
        recyclerUniversities.setLayoutManager(mLayoutManager);
        recyclerUniversities.setAdapter(animeListAdapter);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.VISIBLE);

                strAnimeTitle = etAnimeTitle.getText().toString().trim();

                if (TextUtils.isEmpty(strAnimeTitle)) {
                    Toast.makeText(compatActivity, R.string.please_enter_name, Toast.LENGTH_SHORT).show();
                    progress1.setVisibility(View.GONE);

                    return;
                }


                SharedPrefManager.setPrefVal(v.getContext(), "name", strAnimeTitle);

                animeListViewModel.getAnime(strAnimeTitle, compatActivity);
                animeListViewModel.mutableLiveData
                        .observe(compatActivity, new Observer<AnimeListModule>() {
                            @Override
                            public void onChanged(AnimeListModule animeListModules) {
                                animeListAdapter.setList(animeListModules);
                                animeListAdapter.notifyDataSetChanged();
                                progress1.setVisibility(View.GONE);
                            }
                        });


            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        progress1.setVisibility(View.GONE);

    }
}