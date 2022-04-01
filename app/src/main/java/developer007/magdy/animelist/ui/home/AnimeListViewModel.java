package developer007.magdy.animelist.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import developer007.magdy.animelist.activities.MainActivity;
import developer007.magdy.animelist.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeListViewModel extends ViewModel {

    private static final String TAG = "AnimeListViewModel";
    public MutableLiveData<AnimeListModule> mutableLiveData = new MutableLiveData<>();

    public void getAnime(String name, Context context) {
        AnimeListClient.getInstance().getAnime(name)
                .enqueue(new Callback<AnimeListModule>() {
                    @Override
                    public void onResponse(Call<AnimeListModule> call, Response<AnimeListModule> response) {
                        if (response.isSuccessful() && response.body().results.size() > 0) {
                            mutableLiveData.setValue(response.body());
                        } else {
                            new AlertDialog.Builder(context).setTitle(R.string.error).setMessage("Result: " + response.errorBody().toString())
                                    .setPositiveButton(R.string.ok, (dialog, which) -> {
                                        Log.d(TAG, "onClick: Mgd" + response.message());

                                        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                        context.getApplicationContext().startActivity(i);

                                    }).create().show();
                        }


                    }

                    @Override
                    public void onFailure(Call<AnimeListModule> call, Throwable t) {
                        new AlertDialog.Builder(context).setTitle(R.string.error).setMessage(t.toString())
                                .setPositiveButton(R.string.ok, (dialog, which) -> {
                                    Log.d(TAG, "onClick: Mgd" + t.toString());

                                    Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                    context.getApplicationContext().startActivity(i);

                                }).create().show();
                    }
                });
    }


}