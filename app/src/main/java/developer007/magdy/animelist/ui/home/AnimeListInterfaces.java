package developer007.magdy.animelist.ui.home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnimeListInterfaces {


    @GET("anime")
    public Call<AnimeListModule> getAnime(@Query("q") String name);


}
